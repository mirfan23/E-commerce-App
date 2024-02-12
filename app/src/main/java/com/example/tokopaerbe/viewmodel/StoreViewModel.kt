package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataDetailProduct
import com.example.core.domain.model.DataProduct
import com.example.core.domain.state.UiState
import com.example.core.domain.usecase.AppUseCase
import com.example.core.utils.asMutableStateFLow
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
}