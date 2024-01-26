package com.example.core

import android.content.Context
import com.example.core.remote.service.ApiService
import com.example.core.remote.service.Repository

object Injection {
    fun provideRepository(context: Context) : Repository {
        val apiService = ApiService.getApiService(context)
        return  Repository(apiService)
    }
}