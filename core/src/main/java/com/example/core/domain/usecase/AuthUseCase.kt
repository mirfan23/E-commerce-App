package com.example.core.domain.usecase

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthUseCase {
    suspend fun login(request: LoginRequest): DataLogin
    suspend fun register(request: RegisterRequest): DataToken
    suspend fun uploadProfile(userName: RequestBody, userImage: MultipartBody.Part): DataProfile
    fun saveAccessToken(string: String)
    fun saveRefreshToken(string: String)
//    suspend fun sessionData(): DataSession
}