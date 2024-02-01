package com.example.core.local.preferences

import android.content.Context
import android.content.SharedPreferences

interface SharedPreferencesHelper {

    fun putThemeStatus(value: Boolean)
    fun getThemeStatus(): Boolean
    fun putLanguageStatus(value: String)
    fun getLanguageStatus(): String?
    fun putAccessToken(value: String)
    fun getAccessToken(): String?
    fun putRefreshToken(value: String)
    fun getRefreshToken(): String?
    fun putOnBoardingState(value: Boolean)
    fun getOnBoardingState(): Boolean
    fun clearAllData()
}