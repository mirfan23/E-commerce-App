package com.example.core.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.domain.model.DataFilter
import com.example.core.local.database.Database
import com.example.core.local.entity.ProductEntity
import com.example.core.local.mediator.AppRemoteMediator
import com.example.core.remote.service.ApiEndPoint
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import java.util.Locale

@OptIn(ExperimentalPagingApi::class)
class PagingDataSource(private val apiEndPoint: ApiEndPoint, private val database: Database) {
    fun fetchProduct(
        dataFilter: DataFilter = DataFilter()
    ): Flow<PagingData<ProductEntity>> = Pager(
        config = PagingConfig(
            enablePlaceholders = false,
            pageSize = 10,
            initialLoadSize = 10,
            prefetchDistance = 1
        ),
        remoteMediator = AppRemoteMediator(
            apiEndPoint = apiEndPoint,
            database = database,
            filter = dataFilter
        ),
        pagingSourceFactory = {
            database.appDao().getProductsWithFilter(
                brand = dataFilter.brand?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT) else it.toString() },
                minPrice = dataFilter.lowest,
                maxPrice = dataFilter.highest,
                sort = dataFilter.sort
            )
        }

    ).flow
}