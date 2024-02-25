package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.domain.model.DataFilter
import com.example.core.local.LocalDataSource
import com.example.core.local.PagingDataSource
import com.example.core.local.entity.CartEntity
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.WishListEntity
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.ProductReviewResponse
import com.example.core.utils.safeDataCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class ProductRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val paging: PagingDataSource
) :
    ProductRepository {

    override suspend fun fetchProductLocal(dataFilter: DataFilter): Flow<PagingData<ProductEntity>> = safeDataCall {
        paging.fetchProduct(dataFilter)
    }

    override suspend fun fetchDetailProduct(id: String?): DetailProductResponse = safeDataCall {
        remote.fetchDetailProduct(id)
    }

    override suspend fun fetchProductReview(id: String?): ProductReviewResponse = safeDataCall {
        remote.fetchProductReview(id)
    }

    override suspend fun insertCart(cartEntity: CartEntity) {
        local.insertCart(cartEntity)
    }

    override suspend fun deleteCart(cartEntity: CartEntity) {
        local.deleteCart(cartEntity)
    }

    override suspend fun fetchCart(id: String): Flow<List<CartEntity>> = safeDataCall {
        local.fetchCart(id)
    }

    override suspend fun insertWishList(wishListEntity: WishListEntity) {
        local.insertWishList(wishListEntity)
    }

    override suspend fun fetchWishList(id: String): Flow<List<WishListEntity>> = safeDataCall {
        local.fetchWishList(id)
    }

    override suspend fun deleteWishlist(wishListEntity: WishListEntity) {
        local.deleteWishlist(wishListEntity)
    }

    override fun putWishlistState(state: Boolean) {
        local.putWishlistState(state)
    }

    override fun getWishlistState(): Boolean = local.getWishlistState()
    override suspend fun updateQuantity(cartId: Int, quantity: Int) {
        local.updateQuantity(cartId, quantity)
    }

    override suspend fun updateCheckCart(cartId: Int, value: Boolean) {
        local.updateCheckCart(cartId, value)
    }
}