package com.example.core.domain.repository

import com.example.core.remote.data.ProductResponse

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
}