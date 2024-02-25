package com.example.core.domain.repository

import android.provider.ContactsContract.Data
import androidx.paging.PagingData
import com.example.core.domain.model.DataFilter
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.WishListEntity
import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.ProductReviewResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProductLocal(dataFilter: DataFilter = DataFilter()): Flow<PagingData<ProductEntity>>

    suspend fun fetchDetailProduct(
        id: String? = null
    ): DetailProductResponse

    suspend fun fetchProductReview(
        id: String? = null
    ): ProductReviewResponse

    suspend fun insertCart(cartEntity: CartEntity)

    suspend fun deleteCart(cartEntity: CartEntity)

    suspend fun fetchCart(id: String): Flow<List<CartEntity>>

    suspend fun insertWishList(wishListEntity: WishListEntity)

    suspend fun fetchWishList(id: String): Flow<List<WishListEntity>>

    suspend fun deleteWishlist(wishListEntity: WishListEntity)

    fun putWishlistState(state: Boolean)

    fun getWishlistState(): Boolean

    suspend fun updateQuantity(cartId: Int, quantity: Int)
    suspend fun updateCheckCart(cartId: Int, value: Boolean)

}