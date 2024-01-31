package com.example.core.remote.interceptor

import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.utils.Constant.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val prefs: SharedPreferencesHelper): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val modifiedRequest = when (request.url.encodedPath) {
            "/login", "/register", "/refresh" -> {
                request
                    .newBuilder()
                    .addHeader("API_KEY", API_KEY)
                    .build()
            }

            else -> {
                val accessToken = prefs.getAccessToken()
                request
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
            }
        }
        return chain.proceed(modifiedRequest)
    }
}