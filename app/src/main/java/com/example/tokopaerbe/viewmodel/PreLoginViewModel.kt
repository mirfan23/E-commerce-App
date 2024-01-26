package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.remote.data.ApiState
import com.example.core.remote.data.LoginRequest
import com.example.core.remote.data.LoginResponse
import com.example.core.remote.data.RegisterResponse
import com.example.tokopaerbe.core.remote.service.RegisterRequest
import com.example.core.remote.service.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception

class PreLoginViewModel(private val repo: Repository) : ViewModel() {
    private val _response : MutableLiveData<RegisterResponse> = MutableLiveData()
    val response : LiveData<RegisterResponse> = _response

    private val _reponseLogin : MutableLiveData<LoginResponse> = MutableLiveData()
    val responseLogin : LiveData<LoginResponse> get() = _reponseLogin

    fun fetchRegister(request: RegisterRequest) {
        viewModelScope.launch {
            repo.fetchRegister(request).collectLatest { state ->
                when (state) {
                    is ApiState.Success -> {
                        _response.value = state.data ?: RegisterResponse()
                    }
                    else -> {}
                }
            }
        }
    }

    fun fetchLogin(request: LoginRequest) {
        viewModelScope.launch {
            repo.fetchLogin(request).collectLatest { Loginstate ->
                println("MASUK")
                when (Loginstate) {
                    is ApiState.Success -> {
                        _reponseLogin.value = Loginstate.data ?: LoginResponse()
                    }
                    else -> {}
                }
            }
        }
    }
}