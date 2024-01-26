package com.example.core.remote.service

import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.tokopaerbe.core.remote.service.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiEndPoint {
    @POST("register")
    suspend fun fetchRegister(@Body request: RegisterRequest): RegisterResponse

    @POST("login")
    suspend fun fetchLogin(@Body request: LoginRequest): LoginResponse

    @POST("refresh")
    suspend fun fetchRefresh(@Body request: RefreshTokenRequest) : RefreshTokenResponse

    @POST("profile")
    @Multipart
    suspend fun fetchProfile(
        @Part username: RequestBody,
        @Part userImage: MultipartBody.Part
    ) : ProfileResponse
}