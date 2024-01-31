package com.example.core.domain.repository

import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthRepository {
    suspend fun fetchRegister(request: RegisterRequest): RegisterResponse
    suspend fun fetchLogin(request: LoginRequest): LoginResponse
    suspend fun fetchRefreshToken(request: RefreshTokenRequest): RefreshTokenResponse
    suspend fun fetchUploadProfile(
        username: RequestBody,
        image: MultipartBody.Part
    ): ProfileResponse

//    fun getOnBoardingState(): Boolean
//    fun saveOnBoardingState(state: Boolean)
}