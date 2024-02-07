package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.DataLogin
import com.example.core.domain.model.DataProfile
import com.example.core.domain.model.DataToken
import com.example.core.domain.state.FlowState
import com.example.core.domain.state.UiState
import com.example.core.domain.usecase.AppUseCase
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.asMutableStateFLow
import com.example.tokopaerbe.helper.ValidationHelper.validateEmail
import com.example.tokopaerbe.helper.ValidationHelper.validatePassword
import com.example.tokopaerbe.helper.ValidationHelper.validateRequired
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PreLoginViewModel(private val useCase: AppUseCase) :
    ViewModel() {
    private val _response: MutableStateFlow<UiState<DataToken>> =
        MutableStateFlow(UiState.Empty)
    val response = _response.asStateFlow()

    private val _responseLogin: MutableStateFlow<UiState<DataLogin>> =
        MutableStateFlow(UiState.Empty)
    val responseLogin = _responseLogin.asStateFlow()

    private val _responseProfile: MutableStateFlow<UiState<DataProfile>> =
        MutableStateFlow(UiState.Empty)
    val responseProfile = _responseProfile.asStateFlow()

    private val _validateLoginEmail: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateLoginEmail = _validateLoginEmail.asStateFlow()

    private val _validateLoginPassword: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateLoginPassword = _validateLoginPassword.asStateFlow()

    private val _validateRegisterEmail: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateRegisterEmail = _validateRegisterEmail.asStateFlow()

    private val _validateRegisterPassword: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateRegisterPassword = _validateRegisterPassword.asStateFlow()

    private val _validateProfileName: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateProfileName = _validateProfileName.asStateFlow()

    private val _validateLoginField: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateLoginField = _validateLoginField.asSharedFlow()

    private val _validateRegisterField: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateRegisterField = _validateRegisterField.asSharedFlow()

    private val _validateProfileField: MutableStateFlow<FlowState<Boolean>> =
        MutableStateFlow(FlowState.FlowCreated)
    val validateProfileField = _validateProfileField.asSharedFlow()


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

    fun saveProfileName(dataProfile: DataProfile) {
        useCase.saveProfileName(dataProfile.userName)
    }

    fun validateLoginEmail(email: String) {
        _validateLoginEmail.update { FlowState.FlowValue(email.validateEmail()) }
    }

    fun validateLoginPassword(password: String) {
        _validateLoginPassword.update { FlowState.FlowValue(password.validatePassword()) }
    }

    fun validateRegisterEmail(email: String) {
        _validateRegisterEmail.update { FlowState.FlowValue(email.validateEmail()) }
    }

    fun validateRegisterPassword(password: String) {
        _validateRegisterPassword.update { FlowState.FlowValue(password.validatePassword()) }
    }

    fun validateProfileName(name: String) {
        _validateProfileName.update { FlowState.FlowValue(name.validateRequired()) }
    }

    fun validateLoginField(email: String, password: String) {
        _validateLoginField.update { FlowState.FlowValue(email.validateRequired() && password.validateRequired()) }
    }

    fun validateRegisterField(email: String, password: String) {
        _validateRegisterField.update { FlowState.FlowValue(email.validateRequired() && password.validateRequired()) }
    }

}

