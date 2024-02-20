package com.example.core.local

import com.example.core.local.database.Dao
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.WishListEntity
import com.example.core.local.preferences.SharedPreferenceImpl
import com.example.core.local.preferences.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LocalDataSource(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val dao: Dao
) {

    suspend fun insertCart(cartEntity: CartEntity) {
        dao.insertCart(cartEntity)
    }
    fun fetchCart(id: String): Flow<List<CartEntity>> = dao.retrieveAllCart(id)

    suspend fun deleteCart(cartEntity: CartEntity) {
        dao.deleteCart(cartEntity)
    }

    suspend fun insertWishList(wishListEntity: WishListEntity) {
        dao.insertWishList(wishListEntity)
    }

    fun fetchWishList(id: String): Flow<List<WishListEntity>> = dao.retrieveAllWishList(id)

    suspend fun deleteWishlist(wishListEntity: WishListEntity) {
        dao.deleteWishlist(wishListEntity)
    }

    fun getOnBoardingState(): Boolean = sharedPreferencesHelper.getOnBoardingState()

    fun saveOnBoardingState(state: Boolean) {
        sharedPreferencesHelper.putOnBoardingState(state)
    }

    fun getWishlistState(): Boolean = sharedPreferencesHelper.getWishlistState()

    fun putWishlistState(state: Boolean) {
        sharedPreferencesHelper.putWishlistState(state)
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

    suspend fun updateQuantity(cartId: Int, quantity: Int) = dao.updateQuantity(cartId, quantity)

    suspend fun updateCheckCart(cartId: Int, value: Boolean) = dao.updateCheckCart(cartId, value)
}