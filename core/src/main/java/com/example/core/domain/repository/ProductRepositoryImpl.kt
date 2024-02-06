package com.example.core.domain.repository

import com.example.core.local.LocalDataSource
import com.example.core.remote.RemoteDataSource
import com.example.core.remote.data.ProductResponse
import com.example.core.utils.safeDataCall

class ProductRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) :
    ProductRepository {
    override suspend fun fetchProduct(
        search: String?,
        brand: String?,
        lowestPrice: Int?,
        highestPrice: Int?,
        sort: String?,
        limitItem: Int?,
        page: Int?
    ): ProductResponse = safeDataCall {
        remote.fetchProduct(
            search,
            brand,
            lowestPrice,
            highestPrice,
            sort,
            limitItem,
            page
        )
    }
}