package com.example.core.local

import com.example.core.local.preferences.SharedPreferencesHelper

class LocalDataSource(private val sharedPreferencesHelper: SharedPreferencesHelper) {
    fun getOnBoardingState(): Boolean = sharedPreferencesHelper.getOnBoardingState()

    fun saveOnBoardingState(state: Boolean) {
        sharedPreferencesHelper.putOnBoardingState(state)
    }

//    fun getOnBoardingState(): Boolean = sharedPreferencesHelper.getOnBoardingState()

    fun saveRefreshToken(token: String) {
        sharedPreferencesHelper.putRefreshToken(token)
    }

    fun saveAccessToken(token: String) {
        sharedPreferencesHelper.putAccessToken(token)
    }
}