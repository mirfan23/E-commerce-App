package com.example.core.domain.repository

import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthRepository {
    suspend fun fetchRegister(request: RegisterRequest): RegisterResponse
    suspend fun fetchLogin(request: LoginRequest): LoginResponse
    suspend fun fetchUploadProfile(
        userName: RequestBody,
        userImage: MultipartBody.Part
    ): ProfileResponse
    suspend fun dataSession(name: String, accessToken: String, onBoardingState: Boolean)
    fun getProfileName(): String
    fun saveProfileName(string: String)
    fun getOnBoardingState(): Boolean
    fun saveOnBoardingState(state: Boolean)
    fun getAccessToken(): String
    fun saveAccessToken(string: String)
    fun saveRefreshToken(string: String)
}