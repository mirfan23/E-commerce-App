package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.ViewModel
import com.example.core.local.preferences.SharedPreferencesHelper

class SharedPreferencesViewModel(private val sharedPreferencesHelper: SharedPreferencesHelper) : ViewModel() {

    fun putThemeStatus(value: Boolean) {
        sharedPreferencesHelper.putThemeStatus(value)
    }

    fun getThemeStatus(): Boolean {
        return sharedPreferencesHelper.getThemeStatus()
    }

    fun putLanguageStatus(value: String) {
        sharedPreferencesHelper.putLanguageStatus(value)
    }

    fun getLanguageStatus(): String? {
        return sharedPreferencesHelper.getLanguageStatus()
    }

    fun putAccessToken(value: String) {
        sharedPreferencesHelper.putAccessToken(value)
    }

    fun getAccessToken(): String? {
        return sharedPreferencesHelper.getAccessToken()
    }

    fun putRefreshToken(value: String) {
        sharedPreferencesHelper.putRefreshToken(value)
    }

    fun getRefreshToken(): String? {
        return sharedPreferencesHelper.getRefreshToken()
    }

    fun putOnBoardingState(value: Boolean) {
        sharedPreferencesHelper.putOnBoardingState(value)
    }

    fun getOnBoardingState(): Boolean {
        return sharedPreferencesHelper.getOnBoardingState()
    }

    fun clearAllData() {
        sharedPreferencesHelper.clearAllData()
    }
}
