package com.example.core.domain.repository

import com.example.core.local.LocalDataSource
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.ProfileResponse
import com.example.core.remote.data.RegisterResponse
import com.example.core.remote.RemoteDataSource
import com.example.core.utils.safeDataCall
import com.example.core.remote.data.RegisterRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthRepositoryImpl(private val remote: RemoteDataSource, private val local: LocalDataSource):
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
    ): ProfileResponse = safeDataCall{
        remote.fetchUploadProfile(userName, userImage)
    }

    /**
     * entar kepake
     */

//    override suspend fun getProfileName(): String {
//
//    }
//
//    override suspend fun saveProfileName(string: String) {
//    }
//
//    override suspend fun getOnBoardingState(): Boolean {
//    }
//
//    override suspend fun saveOnBoardingState(state: Boolean) {
//    }
//
//    override suspend fun getAccessToken(): String {
//    }
//
    override fun saveAccessToken(string: String) {
        local.saveAccessToken(string)
    }

    override fun saveRefreshToken(string: String) {
        local.saveRefreshToken(string)
    }

//    override fun getOnBoardingState(): Boolean {
//
//    }
//
//    override fun saveOnBoardingState(state: Boolean) {
//
//    }


}