package com.example.core.local.preferences

import android.content.Context

interface SharedPreferencesHelper {
    fun putAccessToken(value: String)
    fun getAccessToken(): String?
    fun putRefreshToken(value: String)
    fun getRefreshToken() : String?
    fun putOnBoardingState(value: Boolean)
    fun getOnBoardingState(): Boolean
    fun clearAllData()
}