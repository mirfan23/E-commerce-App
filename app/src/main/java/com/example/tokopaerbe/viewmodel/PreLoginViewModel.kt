package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.domain.model.UiState
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.asMutableStateFLow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PreLoginViewModel(private val useCase: AuthUseCase) :
    ViewModel() {
    /**
     * register
     */
    private val _response: MutableStateFlow<UiState<DataToken>> = MutableStateFlow(UiState.Empty)
    val response = _response.asStateFlow()

    /**
     * login
     */
    private val _responseLogin: MutableStateFlow<UiState<DataLogin>> =
        MutableStateFlow(UiState.Empty)
    val responseLogin = _responseLogin.asStateFlow()

    /**
     * profile
     */
    private val _responseProfile: MutableStateFlow<UiState<DataProfile>> =
        MutableStateFlow(UiState.Empty)
    val responseProfile = _responseProfile.asStateFlow()

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

    fun fetchProfile(userName: RequestBody, userImage: MultipartBody.Part) {
        println("Masuk ViewModel")
        viewModelScope.launch {
            _responseProfile.asMutableStateFLow {
                useCase.uploadProfile(userName = userName, userImage = userImage)
            }
        }
    }

    fun saveSession(dataToken: DataToken) {
        useCase.saveAccessToken(dataToken.accessToken)
        useCase.saveRefreshToken(dataToken.refreshToken)
    }
}