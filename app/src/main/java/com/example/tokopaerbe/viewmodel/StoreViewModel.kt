package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataWishList
import com.example.core.domain.state.UiState
import com.example.core.domain.usecase.AppUseCase
import com.example.core.utils.asMutableStateFLow
import com.example.tokopaerbe.helper.toBase64
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StoreViewModel(private val useCase: AppUseCase) : ViewModel() {
    private val _responseDetail: MutableStateFlow<UiState<DataDetailProduct>> =
        MutableStateFlow(UiState.Empty)
    val responseDetail = _responseDetail.asStateFlow()

    fun fetchProduct() = runBlocking { useCase.fetchProduct() }

    fun fetchDetail(productId: String) {
        viewModelScope.launch {
            _responseDetail.asMutableStateFLow {
                useCase.fetchDetailProduct(productId)
            }
        }
    }

    fun insertCart(dataCart: DataCart) {
        val username = useCase.getProfileName()
        viewModelScope.launch { useCase.insertCart(dataCart.copy(userId = username.toBase64())) }
    }

    fun fetchCart() = runBlocking {
        val username = useCase.getProfileName()
        useCase.fetchCart(username.toBase64())
    }

    fun insertWishList(dataWishList: DataWishList) {
        val username = useCase.getProfileName()
        viewModelScope.launch { useCase.insertWishList(dataWishList.copy(userId = username.toBase64())) }
    }

    fun fetchWishList() = runBlocking {
        val username = useCase.getProfileName()
        useCase.fetchWishList(username.toBase64())
    }

    fun removeCart(dataCart: DataCart) {
        viewModelScope.launch {
            useCase.deleteCart(dataCart)
        }
    }

    fun removeWishlist(dataWishList: DataWishList) {
        viewModelScope.launch {
            useCase.deleteWishlist(dataWishList)
        }
    }
}