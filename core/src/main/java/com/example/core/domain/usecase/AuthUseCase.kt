package com.example.core.domain.usecase

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RegisterRequest
import kotlinx.coroutines.flow.update
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AuthUseCase {
    suspend fun login(request: LoginRequest): DataLogin
    suspend fun register(request: RegisterRequest): DataToken
    suspend fun uploadProfile(userName: RequestBody, userImage: MultipartBody.Part): DataProfile
    fun dataSession(): DataSession

    fun saveAccessToken(string: String)
    fun saveRefreshToken(string: String)
    fun saveProfileName(string: String)
    fun getProfileName(): String
    fun saveOnBoardingState(value: Boolean)
    fun getOnBoardingState(): Boolean
    fun putThemeStatus(value: Boolean)
    fun getThemeStatus(): Boolean
    fun putLanguageStatus(value: String)
    fun getLanguageStatus(): String
    fun putAccessToken(value: String)
    fun getAccessToken(): String?
    fun putRefreshToken(value: String)
    fun getRefreshToken(): String?
    fun clearAllSession()
}