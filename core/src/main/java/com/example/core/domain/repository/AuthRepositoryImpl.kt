package com.example.core.domain.repository

import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.remote.data.RefreshTokenResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.datasource.RemoteDataSource
import com.example.core.utils.safeDataCall
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthRepositoryImpl(private val remote: RemoteDataSource):
    AuthRepository {
    override suspend fun fetchLogin(request: LoginRequest): LoginResponse = safeDataCall {
       remote.fetchLogin(request)
    }

    override suspend fun fetchRegister(request: RegisterRequest): RegisterResponse = safeDataCall {
        remote.fetchRegister(request)
    }

    override suspend fun fetchRefreshToken(request: RefreshTokenRequest): RefreshTokenResponse = safeDataCall {
        remote.fetchRefreshToken(request)
    }

    override suspend fun fetchUploadProfile(
        username: RequestBody,
        image: MultipartBody.Part
    ): ProfileResponse = safeDataCall{
        remote.fetchUploadProfile(username, image)
    }

//    override fun getOnBoardingState(): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun saveOnBoardingState(state: Boolean) {
//        TODO("Not yet implemented")
//    }


}