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
        username: RequestBody,
        image: MultipartBody.Part
    ): ProfileResponse
    /**
     * entar dipake
     */
//    suspend fun getProfileName(): String
//    suspend fun saveProfileName(string: String)
//    suspend fun getOnBoardingState(): Boolean
//    suspend fun saveOnBoardingState(state: Boolean)
//    suspend fun getAccessToken(): String
//    suspend fun saveAccessToken(string: String)
}