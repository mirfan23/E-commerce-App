package com.example.tokopaerbe.helper

import android.content.Context
import android.content.SharedPreferences

class Helper {
    companion object {
        private const val SHARED_PREF_FILE = "userPreference"

        fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        }

        fun putObstatus(context: Context, key: String, value: Boolean) {
            getSharedPreferences(context).edit().putBoolean(key, value).apply()
        }

        fun getObstatus(context: Context, key: String): Boolean {
            return getSharedPreferences(context).getBoolean(key,false)
        }

        fun putThemeStatus(context: Context, key: String, value: Boolean) {
            getSharedPreferences(context).edit().putBoolean(key, value).apply()
        }
        fun getThemeStatus(context: Context, key: String): Boolean {
            return getSharedPreferences(context).getBoolean(key, false)
        }

        fun putLanguageStatus(context: Context, key: String, value: String) {
            getSharedPreferences(context).edit().putString(key, value).apply()
        }
        fun getLanguageStatus(context: Context, key: String): String? {
            return getSharedPreferences(context).getString(key, "")
        }
    }
}