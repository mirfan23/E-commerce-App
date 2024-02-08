package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.local.entity.ProductEntity
import com.example.core.remote.data.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun fetchProduct(
        search: String? = null,
        brand: String? = null,
        lowestPrice: Int? = null,
        highestPrice: Int? = null,
        sort: String? = null,
        limitItem: Int? = null,
        page: Int? = null
    ): ProductResponse

    suspend fun fetchProductLocal(): Flow<PagingData<ProductEntity>>
}