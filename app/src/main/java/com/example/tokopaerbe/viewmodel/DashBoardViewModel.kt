package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.DataSession
import com.example.core.domain.state.SplashState
import com.example.core.domain.usecase.AppUseCase
import com.example.core.utils.DataMapper.toSplashState
import com.example.tokopaerbe.helper.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashBoardViewModel(private val useCase: AppUseCase) : ViewModel() {
    private  val _theme = MutableStateFlow(false)
    val theme = _theme.asStateFlow()

    private  val _language = MutableStateFlow(false)
    val language = _language.asStateFlow()

    private  val _onBoarding = MutableStateFlow<SplashState<DataSession>>(SplashState.OnBoarding)
    val onBoarding = _onBoarding.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun putProfileName() {
        _name.update { useCase.getProfileName() }
    }

    fun putThemeStatus(value: Boolean) {
        useCase.putThemeStatus(value)
    }

    fun getThemeStatus() {
        _theme.update { useCase.getThemeStatus() }
    }

    fun putLanguageStatus(value: String) {
        useCase.putLanguageStatus(value)
    }

    fun getLanguageStatus(): Boolean {
        return useCase.getLanguageStatus().equals(Constant.LANGUAGE_IN, true)
    }
    fun putOnBoardingState(value: Boolean) {
        useCase.saveOnBoardingState(value)
    }

    fun getOnBoardingState(){
        _onBoarding.update { useCase.dataSession().toSplashState() }
    }

    fun clearAllSession() {
        useCase.clearAllSession()
    }
}
