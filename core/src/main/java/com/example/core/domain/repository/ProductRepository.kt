package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.local.entity.ProductEntity
import com.example.core.remote.data.DetailProductResponse
import com.example.core.remote.data.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProductLocal(): Flow<PagingData<ProductEntity>>

    suspend fun fetchDetailProduct(
        id: String? = null
    ): DetailProductResponse
}