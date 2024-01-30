package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataToken
import com.example.core.domain.model.UiState
import com.example.core.domain.usecase.AuthInteractor
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.asMutableStateFLow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PreLoginViewModel(private val useCase: AuthUseCase) :
    ViewModel() {

    private val _response: MutableStateFlow<UiState<DataToken>> = MutableStateFlow(UiState.Empty)
    val response = _response.asStateFlow()

    private val _responseLogin: MutableStateFlow<UiState<DataLogin>> =
        MutableStateFlow(UiState.Empty)
    val responseLogin = _responseLogin.asStateFlow()

    fun fetchRegister(requestRegister: RegisterRequest) {
        viewModelScope.launch {
            _response.asMutableStateFLow {
                useCase.register(request = requestRegister)
            }
        }
    }

    fun fetchLogin(requestLogin: LoginRequest) {
        viewModelScope.launch {
            _responseLogin.asMutableStateFLow {
                useCase.login(request = requestLogin)
            }
        }
    }
}