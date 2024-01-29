package com.example.core.remote.datasource

import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.service.ApiEndPoint
import com.example.core.utils.safeApiCall
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RemoteDataSource(private val apiEndPoint: ApiEndPoint) {
    suspend fun fetchLogin (
        request: LoginRequest
    ): LoginResponse {
        return safeApiCall { apiEndPoint.fetchLogin(request) }
    }

    suspend fun fetchRegister (
        request: RegisterRequest
    ): RegisterResponse {
        return safeApiCall { apiEndPoint.fetchRegister(request) }
    }

    suspend fun fetchRefreshToken (
        request: RefreshTokenRequest
    ): RefreshTokenResponse {
        return safeApiCall { apiEndPoint.fetchRefreshToken(request) }
    }

    suspend fun fetchUploadProfile(
        username: RequestBody,
        userImage: MultipartBody.Part
    ): ProfileResponse {
        return safeApiCall { apiEndPoint.fetchProfile(username, userImage) }
    }
}