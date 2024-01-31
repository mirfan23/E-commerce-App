package com.example.core.remote.service

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("API_KEY", apiKey)
            .build()

        return chain.proceed(modifiedRequest)
    }
}
