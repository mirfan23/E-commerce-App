package com.example.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProduct
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataReviewProduct
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.repository.ProductRepository
import com.example.core.domain.state.UiState
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.data.LoginRequest
import com.example.core.utils.DataMapper.toUIData
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.DataMapper.toUIListData
import com.example.core.utils.safeDataCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AppInteractor(
    private val repository: AuthRepository,
    private val productRepo: ProductRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) :
    AppUseCase {

    override suspend fun login(request: LoginRequest): DataLogin =
        safeDataCall {
            println("MASUK Interactor: $request")
            repository.fetchLogin(request).toUIData()
        }

    override suspend fun register(request: RegisterRequest): DataToken =
        safeDataCall {
            repository.fetchRegister(request).toUIData()
        }

    override suspend fun uploadProfile(
        userName: RequestBody,
        userImage: MultipartBody.Part
    ): DataProfile =
        safeDataCall {
            repository.fetchUploadProfile(userName, userImage).toUIData()
        }

    override fun dataSession(): DataSession {
        val name = repository.getProfileName()
        val accessToken = repository.getAccessToken()
        val onBoardingState = repository.getOnBoardingState()
        val triple: Triple<String, String, Boolean> = Triple(name, accessToken, onBoardingState)
        return triple.toUIData()
    }

    override suspend fun fetchProduct(): Flow<UiState<PagingData<DataProduct>>> = safeDataCall {
        productRepo.fetchProductLocal().map { data ->
            val mapped = data.map { entity -> entity.toUIData() }
            UiState.Success(mapped)
        }.flowOn(Dispatchers.IO).catch { throwable -> UiState.Error(throwable) }
    }

    override suspend fun fetchDetailProduct(
        productId: String
    ): DataDetailProduct = safeDataCall {
        productRepo.fetchDetailProduct(productId).data.toUIData()
    }

    override suspend fun fetchProductReview(
        productId: String
    ): List<DataReviewProduct> = safeDataCall {
        productRepo.fetchProductReview(productId).data.toUIListData()
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