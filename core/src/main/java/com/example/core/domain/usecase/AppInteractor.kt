package com.example.core.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataFilter
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataPayment
import com.example.core.domain.model.DataPaymentItem
import com.example.core.domain.model.DataProduct
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataReviewProduct
import com.example.core.domain.model.DataSession
import com.example.core.domain.model.DataToken
import com.example.core.domain.model.DataWishList
import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.repository.FirebaseRepository
import com.example.core.domain.repository.ProductRepository
import com.example.core.domain.state.UiState
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.PaymentResponse
import com.example.core.utils.DataMapper.toUIData
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.DataMapper.toEntity
import com.example.core.utils.DataMapper.toUIListData
import com.example.core.utils.safeDataCall
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import okhttp3.RequestBody

class AppInteractor(
    private val repository: AuthRepository,
    private val productRepo: ProductRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val firebaseRepository: FirebaseRepository
) :
    AppUseCase {

    override suspend fun login(request: LoginRequest): DataLogin =
        safeDataCall {
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

    override suspend fun fetchProduct(dataFilter: DataFilter): Flow<UiState<PagingData<DataProduct>>> =
        safeDataCall {
            productRepo.fetchProductLocal(dataFilter).map { data ->
                val mapped = data.map { entity ->
                    entity.toUIData()
                }
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

    override suspend fun insertCart(productCart: DataCart) {
        productRepo.insertCart(productCart.toEntity())
    }

    override suspend fun deleteCart(dataCart: DataCart) {
        productRepo.deleteCart(dataCart.toEntity())
    }

    override suspend fun fetchCart(id: String): Flow<UiState<List<DataCart>>> = safeDataCall {
        productRepo.fetchCart(id).map { data ->
            val mapped = data.map { cartEntity -> cartEntity.toUIData() }
            UiState.Success(mapped)
        }.flowOn(Dispatchers.IO).catch { throwable -> UiState.Error(throwable) }
    }

    override suspend fun insertWishList(dataWishList: DataWishList) {
        productRepo.insertWishList(dataWishList.toEntity())
    }

    override suspend fun fetchWishList(id: String): Flow<UiState<List<DataWishList>>> =
        safeDataCall {
            productRepo.fetchWishList(id).map { data ->
                val mapped = data.map { wishListEntity -> wishListEntity.toUIData() }
                UiState.Success(mapped)
            }.flowOn(Dispatchers.IO).catch { throwable -> UiState.Error(throwable) }
        }

    override suspend fun deleteWishlist(dataWishList: DataWishList) {
        productRepo.deleteWishlist(dataWishList.toEntity())
    }

    override suspend fun updateQuantity(cartId: Int, quantity: Int) {
        productRepo.updateQuantity(
            cartId = cartId,
            quantity = quantity
        )
    }

    override suspend fun getConfigPayment(): Flow<List<DataPayment>> = flow {
        val response = Gson().fromJson(firebaseRepository.getConfigPayment(), PaymentResponse::class.java)
        println("MASUK: USECASE: $response")
        emit(response.data.map { data -> data.toUIData() }.toList())
    }.flowOn(Dispatchers.IO)

    override suspend fun getConfigStatusUpdate(): Flow<Boolean> = firebaseRepository.getConfigStatusUpdate()

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
    override fun putWishlistState(value: Boolean) {
        productRepo.putWishlistState(value)
    }

    override fun getWishlistState(): Boolean = productRepo.getWishlistState()


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

    override suspend fun updateCheckCart(cartId: Int, value: Boolean) {
        productRepo.updateCheckCart(cartId, value)
    }
}