//package com.example.core.local.mediator
//
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import com.example.core.local.entity.ProductEntity
//import com.example.core.local.preferences.Dao
//import com.example.core.remote.service.ApiEndPoint
//
//@OptIn(ExperimentalPagingApi::class)
//class AppRemoteMediator(
//    private val apiEndPoint: ApiEndPoint,
//    private val dao: Dao
//) : RemoteMediator<Int, ProductEntity>() {
//
//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, ProductEntity>
//    ): MediatorResult {
//        val page = when (loadType) {
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosetToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
//            }
//
//            LoadType.PREPEND -> {
//                val remoteKeys = getRemoteKeyForFirstItem(state)
//                val prevKey = remoteKeys?.prevKey
//                    ?: return  MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                prevKey
//            }
//        }
//    }
//}