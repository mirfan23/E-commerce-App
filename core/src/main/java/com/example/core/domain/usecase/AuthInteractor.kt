package com.example.core.domain.usecase

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.domain.repository.AuthRepository
import com.example.core.local.preferences.SharedPreferenceImpl
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RefreshTokenRequest
import com.example.core.utils.DataMapper.toUIData
import com.example.core.remote.data.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AuthInteractor(private val repository: AuthRepository, private val sharedPreferencesHelper: SharedPreferencesHelper):
    AuthUseCase {

    override suspend fun login(request: LoginRequest): DataLogin =
        withContext(Dispatchers.IO) {
            repository.fetchLogin(request).toUIData()
        }

    override suspend fun register(request: RegisterRequest): DataToken =
        withContext(Dispatchers.IO) {
            repository.fetchRegister(request).toUIData()
        }

    override suspend fun refreshToken(request: RefreshTokenRequest): DataToken =
        withContext(Dispatchers.IO) {
            repository.fetchRefreshToken(request).toUIData()
        }

    override suspend fun uploadProfile(username: RequestBody, userImage: MultipartBody.Part): DataProfile =
        withContext(Dispatchers.IO){
            repository.fetchUploadProfile(username, userImage).toUIData()
        }
}