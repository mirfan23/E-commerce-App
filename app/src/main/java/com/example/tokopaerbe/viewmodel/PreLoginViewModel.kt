package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.remote.data.ApiState
import com.example.core.remote.data.RegisterResponse
import com.example.tokopaerbe.core.remote.service.RegisterRequest
import com.example.core.remote.service.Repository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

class PreLoginViewModel(private val repo: com.example.core.remote.service.Repository) : ViewModel() {
    private val _response : MutableLiveData<com.example.core.remote.data.RegisterResponse> = MutableLiveData()
    val response : LiveData<com.example.core.remote.data.RegisterResponse> = _response

    fun fetchRegister(request: RegisterRequest) {
        viewModelScope.launch {
            repo.fetchRegister(request).collectLatest { state ->
                when (state) {
                    is com.example.core.remote.data.ApiState.Success -> {
                        _response.value = state.data ?: com.example.core.remote.data.RegisterResponse()
                    }
                    else -> {}
                }
            }
        }
    }
}