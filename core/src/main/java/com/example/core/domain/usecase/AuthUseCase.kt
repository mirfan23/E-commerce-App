package com.example.core.domain.usecase

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthUseCase {
    suspend fun login(request: LoginRequest): DataLogin
    suspend fun register(request: RegisterRequest): DataToken
    suspend fun refreshToken(request: RefreshTokenRequest): DataToken
    suspend fun uploadProfile(username: RequestBody, userImage: MultipartBody.Part): DataProfile
}