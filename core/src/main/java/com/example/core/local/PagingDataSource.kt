package com.example.core.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.local.database.Database
import com.example.core.local.entity.ProductEntity
import com.example.core.local.mediator.AppRemoteMediator
import com.example.core.remote.service.ApiEndPoint
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class PagingDataSource(private val apiEndPoint: ApiEndPoint, private val database: Database) {
    fun fetchProduct(): Flow<PagingData<ProductEntity>> = Pager(
        config = PagingConfig(
            enablePlaceholders = false,
            pageSize = 10,
            initialLoadSize = 10,
            prefetchDistance = 1
        ),
        remoteMediator = AppRemoteMediator(apiEndPoint = apiEndPoint, database = database),
        pagingSourceFactory = {
            database.appDao().retrieveAllProducts()
        }
    ).flow
}