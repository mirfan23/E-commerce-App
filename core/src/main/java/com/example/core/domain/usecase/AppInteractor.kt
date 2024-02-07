package com.example.core.domain.usecase

import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProduct
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.repository.ProductRepository
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.data.LoginRequest
import com.example.core.utils.DataMapper.toUIData
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.DataMapper.toUIListData
import com.example.core.utils.safeDataCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AppInteractor(
    private val repository: AuthRepository,
    private val productRepo: ProductRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) :
    AppUseCase {

    override suspend fun login(request: LoginRequest): DataLogin =
        withContext(Dispatchers.IO) {
            repository.fetchLogin(request).toUIData()
        }

    override suspend fun register(request: RegisterRequest): DataToken =
        withContext(Dispatchers.IO) {
            repository.fetchRegister(request).toUIData()
        }

    override suspend fun uploadProfile(
        userName: RequestBody,
        userImage: MultipartBody.Part
    ): DataProfile =
        withContext(Dispatchers.IO) {
            repository.fetchUploadProfile(userName, userImage).toUIData()
        }

    override fun dataSession(): DataSession {
        val name = repository.getProfileName()
        val accessToken = repository.getAccessToken()
        val onBoardingState = repository.getOnBoardingState()
        val triple: Triple<String, String, Boolean> = Triple(name, accessToken, onBoardingState)
        return triple.toUIData()
    }

    override suspend fun getProduct(
        search: String?,
        brand: String?,
        lowestPrice: Int?,
        highestPrice: Int?,
        sort: String?,
        limitItem: Int?,
        page: Int?
    ): List<DataProduct> = safeDataCall {
        productRepo.fetchProduct(
            search,
            brand,
            lowestPrice,
            highestPrice,
            sort,
            limitItem,
            page
        ).toUIListData()
    }


    override fun saveAccessToken(string: String) {
        repository.saveAccessToken(string)
    }

    override fun saveRefreshToken(string: String) {
        repository.saveRefreshToken(string)
    }

    override fun saveProfileName(string: String) {
        repository.saveProfileName(string)
    }

    override fun getProfileName(): String = repository.getProfileName()

    override fun saveOnBoardingState(value: Boolean) {
        repository.saveOnBoardingState(value)
    }

    override fun getOnBoardingState(): Boolean = repository.getOnBoardingState()


    override fun putThemeStatus(value: Boolean) {
        sharedPreferencesHelper.putThemeStatus(value)
    }

    override fun getThemeStatus(): Boolean = sharedPreferencesHelper.getThemeStatus()


    override fun putLanguageStatus(value: String) {
        sharedPreferencesHelper.putLanguageStatus(value)
    }

    override fun getLanguageStatus(): String = sharedPreferencesHelper.getLanguageStatus()

    override fun putAccessToken(value: String) {
        sharedPreferencesHelper.putAccessToken(value)
    }

    override fun getAccessToken(): String = sharedPreferencesHelper.getAccessToken()


    override fun putRefreshToken(value: String) {
        sharedPreferencesHelper.putRefreshToken(value)
    }

    override fun getRefreshToken(): String = sharedPreferencesHelper.getRefreshToken()

    override fun clearAllSession() {
        sharedPreferencesHelper.clearAllSession()
    }
}