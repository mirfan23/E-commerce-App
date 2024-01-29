package com.example.core.di

import androidx.viewbinding.BuildConfig
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.remote.interceptor.AuthInterceptor
import com.example.core.remote.interceptor.SessionInterceptor
import com.example.core.remote.interceptor.TokenInterceptor
import com.example.core.remote.service.ApiEndPoint
import com.example.core.utils.Constant.BASE_URL
import okhttp3.OkHttpClient
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val chuckerInterceptor = ChuckerInterceptor.Builder(context = get())
                    .collector(ChuckerCollector(context = get()))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
                addInterceptor(chuckerInterceptor)
            }
            addInterceptor(AuthInterceptor(get()))
            addInterceptor(SessionInterceptor(get()))
            authenticator(TokenInterceptor(get(), get()))
        }.build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiEndPoint::class.java)
    }
}