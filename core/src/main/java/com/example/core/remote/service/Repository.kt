package com.example.core.remote.service

import com.example.core.remote.data.ApiState
import com.example.core.remote.data.RegisterResponse
import com.example.tokopaerbe.core.remote.service.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class Repository(private val api: ApiEndPoint) {
    fun fetchRegister(request: RegisterRequest): Flow<ApiState<RegisterResponse>> = flow {
        emit(ApiState.Loading)
        try {
            val response = api.fetchRegister(request)
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            println("APA SIH : ${e.message}")
            when {
                e is HttpException -> {
                    ApiState.Error(e.code(), e.message())
                }

                e is IOException -> {
                    ApiState.Error(600, "No Connection")
                }

                else -> {
                    ApiState.Error(500, "")
                }
            }
        }
    }
}