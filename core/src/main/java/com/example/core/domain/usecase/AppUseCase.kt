package com.example.core.domain.usecase

import androidx.paging.PagingData
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProduct
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.domain.state.UiState
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RegisterRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface AppUseCase {
    suspend fun login(request: LoginRequest): DataLogin
    suspend fun register(request: RegisterRequest): DataToken
    suspend fun uploadProfile(userName: RequestBody, userImage: MultipartBody.Part): DataProfile
    suspend fun getProduct(
        search: String? = null,
        brand: String? = null,
        lowestPrice: Int? = null,
        highestPrice: Int? = null,
        sort: String? = null,
        limitItem: Int? = null,
        page: Int? = null
    ): List<DataProduct>

    suspend fun fetchProduct(): Flow<UiState<PagingData<DataProduct>>>

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