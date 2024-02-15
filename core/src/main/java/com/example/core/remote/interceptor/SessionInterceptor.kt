package com.example.core.remote.interceptor

import com.example.core.local.preferences.SharedPreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response

class SessionInterceptor( private val prefs: SharedPreferencesHelper) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)
        return response
    }
}