package com.example.core.remote.interceptor

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.service.ApiEndPoint
import com.example.core.utils.Constant.API_KEY
import com.example.core.utils.Constant.BASE_URL
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TokenInterceptor(
    private val prefs: SharedPreferencesHelper,
    private val chuckerInterceptor: ChuckerInterceptor
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = prefs.getRefreshToken()

        synchronized(this) {
            return runBlocking {
                try {
                    val newToken = refreshToken(RefreshTokenRequest(refreshToken))
                    prefs.putRefreshToken(newToken.data.refreshToken)
                    prefs.putAccessToken(newToken.data.accessToken)
                    response.request
                        .newBuilder()
                        .header("Authorization", "Bearer ${newToken.data.accessToken}")
                        .build()
                } catch (error: Throwable) {
                    response.close()
                    null
                }
            }
        }
    }

    private suspend fun refreshToken(tokenRequest: RefreshTokenRequest): RefreshTokenResponse {
        val interceptor = Interceptor.invoke { chain ->
            val request = chain
                .request()
                .newBuilder()
                .addHeader("API_KEY", API_KEY)
                .build()
            chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(interceptor)
            .build()

        val apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiEndPoint::class.java)

        try {
            val newRequest = apiService.fetchRefreshToken(tokenRequest)
            prefs.putAccessToken(newRequest.data.accessToken)
            prefs.putRefreshToken(newRequest.data.refreshToken)

            return newRequest
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}