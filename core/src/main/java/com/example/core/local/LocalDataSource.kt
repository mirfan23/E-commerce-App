package com.example.core.local

import com.example.core.local.preferences.SharedPreferencesHelper

class LocalDataSource(private val sharedPreferencesHelper: SharedPreferencesHelper) {
    fun getOnBoardingState(): Boolean = sharedPreferencesHelper.getOnBoardingState()

    fun saveOnBoardingState(state: Boolean) {
        sharedPreferencesHelper.putOnBoardingState(state)
    }
}