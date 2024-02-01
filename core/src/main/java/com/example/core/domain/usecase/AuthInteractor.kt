package com.example.core.domain.usecase

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.domain.repository.AuthRepository
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.data.LoginRequest
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

    override suspend fun uploadProfile(userName: RequestBody, userImage: MultipartBody.Part): DataProfile =
        withContext(Dispatchers.IO){
            repository.fetchUploadProfile(userName, userImage).toUIData()
        }

    override fun saveAccessToken(string: String) {
        repository.saveAccessToken(string)
    }

    override fun saveRefreshToken(string: String) {
        repository.saveRefreshToken(string)
    }


//    override suspend fun sessionData(): DataSession {
//        val name = repository.getProfileName()
//        val accessToken = repository.getAccessToken()
//        val onBoardingState = repository.getOnBoardingState()
//        val triple: Triple<String, String, Boolean> = Triple(name,accessToken,onBoardingState)
//        return triple.toUIData()
//    }
}