package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataCart
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataDetailVariantProduct
import com.example.core.domain.model.DataWishList
import com.example.core.domain.state.FlowState
import com.example.core.domain.state.UiState
import com.example.core.domain.usecase.AppUseCase
import com.example.core.utils.asMutableStateFLow
import com.example.tokopaerbe.helper.toBase64
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class StoreViewModel(private val useCase: AppUseCase) : ViewModel() {
    private val _responseDetail: MutableStateFlow<UiState<DataDetailProduct>> =
        MutableStateFlow(UiState.Empty)
    val responseDetail = _responseDetail.asStateFlow()

    private val _wishlist = MutableStateFlow(false)
    val wishlist = _wishlist.asStateFlow()

    private val _cartQuantity = MutableStateFlow(0)
    val cartQuantity = _cartQuantity.asStateFlow()

    private val _totalPrice: MutableStateFlow<FlowState<Int>> =
        MutableStateFlow(FlowState.FlowCreated)
    val totalPrice = _totalPrice.asStateFlow()

    private var dataCart: DataCart? = null
    private var dataWishList: DataWishList? = null

    fun fetchProduct() = runBlocking { useCase.fetchProduct() }

    fun fetchDetail(productId: String) {
        viewModelScope.launch {
            _responseDetail.asMutableStateFLow {
                useCase.fetchDetailProduct(productId)
            }
        }
    }

    fun insertCart(variant: DataDetailVariantProduct) {
        val username = useCase.getProfileName()
        viewModelScope.launch {
            dataCart?.copy(
                variant = variant.variantName, productPrice = dataCart?.productPrice?.plus(
                    variant.variantPrice
                ) ?: 0
            )
            dataCart?.let {
                useCase.insertCart(
                    it.copy(userId = username.toBase64())
                )
            }
        }
    }

    fun fetchCart() = runBlocking {
        val username = useCase.getProfileName()
        useCase.fetchCart(username.toBase64())
    }

    fun insertWishList() {
        val username = useCase.getProfileName()
        viewModelScope.launch { dataWishList?.let { useCase.insertWishList(it.copy(userId = username.toBase64())) } }
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

    fun removeWishlistDetail() {
        viewModelScope.launch {
            dataWishList?.let {
                useCase.deleteWishlist(it)
                println("MASUK ANU")
            }
        }
    }

    fun putWishlistState(value: Boolean) {
        useCase.putWishlistState(value)
    }

    fun getWishlistState() {
        _wishlist.update { useCase.getWishlistState() }
    }

    fun updateQuantity(productId: String, quantity: Int) {
        viewModelScope.launch {
            useCase.updateQuantity(productId, quantity)
        }
    }

    fun updateTotalPrice(price: Int) {
        _totalPrice.update { FlowState.FlowValue(price) }
    }

    fun setDataCart(data: DataCart) {
        dataCart = data
    }

    fun setDataWishlist(data: DataWishList) {
        dataWishList = data
    }

    fun removeWishlistWishlist(data: DataWishList) {
        viewModelScope.launch {
            useCase.deleteWishlist(data)
        }
    }
}