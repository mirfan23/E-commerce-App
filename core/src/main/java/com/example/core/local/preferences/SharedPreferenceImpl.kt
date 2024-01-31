package com.example.core.local.preferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceImpl(private val sharedPreferences: SharedPreferences) : SharedPreferencesHelper {
    companion object {
        const val PREFS_NAME = "MySharedPreferences"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
        private const val KEY_ON_BOARDING_STATE = "on_boarding_state"
        private const val LANGUAGE_STATUS= "language_status"
        private const val THEME_STATUS= "theme_status"
    }

    override fun putThemeStatus(value: Boolean) {
        sharedPreferences.edit().putBoolean(THEME_STATUS, value).apply()
    }

    override fun getThemeStatus(): Boolean {
        return sharedPreferences.getBoolean(THEME_STATUS, false)
    }

    override fun putLanguageStatus(value: String) {
        sharedPreferences.edit().putString(LANGUAGE_STATUS, value).apply()
    }

    override fun getLanguageStatus(): String? {
        return sharedPreferences.getString(LANGUAGE_STATUS, null)
    }

    override fun putAccessToken(value: String) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, value).apply()
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun putRefreshToken(value: String) {
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, value).apply()
    }

    override fun getRefreshToken(): String? {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, null)
    }

    override fun putOnBoardingState(value: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_ON_BOARDING_STATE, value).apply()
    }

    override fun getOnBoardingState(): Boolean {
        return sharedPreferences.getBoolean(KEY_ON_BOARDING_STATE, false)
    }

    override fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }

}