package com.example.core.local

import com.example.core.local.database.Dao
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.WishListEntity
import com.example.core.local.preferences.SharedPreferencesHelper
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val dao: Dao
) {

    suspend fun insertCart(cartEntity: CartEntity) {
        dao.insertCart(cartEntity)
    }
    fun fetchCart(): Flow<List<CartEntity>> = dao.retrieveAllCart()

    suspend fun deleteCart(cartEntity: CartEntity) {
        dao.deleteCart(cartEntity)
    }

    suspend fun insertWishList(wishListEntity: WishListEntity) {
        dao.insertWishList(wishListEntity)
    }

    fun fetchWishList(): Flow<List<WishListEntity>> = dao.retrieveAllWishList()

    fun getOnBoardingState(): Boolean = sharedPreferencesHelper.getOnBoardingState()

    fun saveOnBoardingState(state: Boolean) {
        sharedPreferencesHelper.putOnBoardingState(state)
    }

    fun saveRefreshToken(token: String) {
        sharedPreferencesHelper.putRefreshToken(token)
    }

    fun saveAccessToken(token: String) {
        sharedPreferencesHelper.putAccessToken(token)
    }

    fun getAccessToken(): String = sharedPreferencesHelper.getAccessToken()

    fun saveProfileName(name: String) {
        sharedPreferencesHelper.putProfileName(name)
    }

    fun getProfileName(): String = sharedPreferencesHelper.getProfileName()
}