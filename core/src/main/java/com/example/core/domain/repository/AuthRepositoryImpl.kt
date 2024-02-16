package com.example.core.domain.repository

import com.example.core.local.LocalDataSource
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.data.ProductResponse
import com.example.core.utils.safeDataCall
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthRepositoryImpl(private val remote: RemoteDataSource, private val local: LocalDataSource) :
    AuthRepository {
    override suspend fun fetchLogin(request: LoginRequest): LoginResponse = safeDataCall {
        remote.fetchLogin(request)
    }

    override suspend fun fetchRegister(request: RegisterRequest): RegisterResponse = safeDataCall {
        remote.fetchRegister(request)
    }

    override suspend fun fetchUploadProfile(
        userName: RequestBody,
        userImage: MultipartBody.Part
    ): ProfileResponse = safeDataCall {
        remote.fetchUploadProfile(userName, userImage)
    }

    override suspend fun dataSession(name: String, accessToken: String, onBoardingState: Boolean) {}

    override fun getProfileName(): String = local.getProfileName()

    override fun saveProfileName(string: String) {
        local.saveProfileName(string)
    }

    override fun getOnBoardingState(): Boolean = local.getOnBoardingState()

    override fun saveOnBoardingState(state: Boolean) {
        local.saveOnBoardingState(state)
    }

    override fun getAccessToken(): String = local.getAccessToken()

    override fun saveAccessToken(string: String) {
        local.saveAccessToken(string)
    }

    override fun saveRefreshToken(string: String) {
        local.saveRefreshToken(string)
    }
}