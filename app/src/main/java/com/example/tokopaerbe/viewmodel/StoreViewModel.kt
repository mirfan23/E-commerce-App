package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataProduct
import com.example.core.domain.state.UiState
import com.example.core.domain.usecase.AppUseCase
import com.example.core.utils.asMutableStateFLow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoreViewModel(private val useCase: AppUseCase) : ViewModel() {
    private val _responseProduct: MutableStateFlow<UiState<List<DataProduct>>> =
        MutableStateFlow(UiState.Empty)
    val responseProduct = _responseProduct.asStateFlow()
    fun fetchProduct(
        limitItem: Int? = null,
        page: Int? = null
    ) {
        viewModelScope.launch {
            _responseProduct.asMutableStateFLow {
                useCase.getProduct(limitItem = limitItem, page = page)
            }

        }
    }
}