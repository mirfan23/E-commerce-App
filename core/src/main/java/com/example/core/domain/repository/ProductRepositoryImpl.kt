package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.local.LocalDataSource
import com.example.core.local.PagingDataSource
import com.example.core.local.entity.ProductEntity
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.ProductReviewResponse
import com.example.core.utils.safeDataCall
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val paging: PagingDataSource
) :
    ProductRepository {

    override suspend fun fetchProductLocal(): Flow<PagingData<ProductEntity>> = safeDataCall {
        paging.fetchProduct()
    }

    override suspend fun fetchDetailProduct(id: String?): DetailProductResponse = safeDataCall {
        remote.fetchDetailProduct(id)
    }

    override suspend fun fetchProductReview(id: String?): ProductReviewResponse = safeDataCall {
        remote.fetchProductReview(id)
    }
}