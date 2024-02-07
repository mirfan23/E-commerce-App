package com.example.core.local.mediator


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.core.local.entity.ProductEntity
import com.example.core.local.entity.RemoteKeys
import com.example.core.local.database.Database
import com.example.core.remote.service.ApiEndPoint
import com.example.core.utils.Constant.INITIAL_PAGE_INDEX
import com.example.core.utils.DataMapper.toLocalListData
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class AppRemoteMediator(
    private val apiEndPoint: ApiEndPoint,
    private val database: Database
) : RemoteMediator<Int, ProductEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductEntity>
    ): MediatorResult {
        val page = when (loadType) {

            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosetToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return  MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return  MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }
        return try {
            val responseData = apiEndPoint.fetchProduct(limitItem = state.config.initialLoadSize, page = page)
            val endOfPaginationReached = responseData.data.items.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.appDao().deleteAllKey()
                    database.appDao().deleteAll()
                }
                val prevKey = if (page == 1) null else - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = responseData.data.items.map {
                    RemoteKeys(id = it. productId, prevKey = prevKey, nextKey = nextKey)
                }
                database.appDao().insertAll(keys)
                database.appDao().insertProduct(responseData.toLocalListData())
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e:Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ProductEntity>):RemoteKeys? {
        return state .pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()?.let { data ->
            database.appDao().getRemoteKeysId(data.productId)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ProductEntity>):RemoteKeys? {
        return state.pages.firstOrNull{it.data.isNotEmpty()}?.data?.firstOrNull()?.let { data ->
            database.appDao().getRemoteKeysId(data.productId)
        }
    }

    private suspend fun getRemoteKeyClosetToCurrentPosition(state: PagingState<Int, ProductEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.productId?.let { id ->
                database.appDao().getRemoteKeysId(id)
            }
        }
    }
}