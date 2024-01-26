package com.example.tokopaerbe.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokopaerbe.helper.Helper
import kotlinx.coroutines.launch

class DashBoardViewModel(private val context: Context) : ViewModel() {

    companion object {
        private const val THEME_KEY = "dark"
        private const val TAG = "DashBoardViewModel"
    }

    private val sharedPreferences: SharedPreferences
        get() = Helper.getSharedPreferences(context)

    val appThemeLiveData = MutableLiveData<Boolean>()

    fun setThemeDark(isActive: Boolean) {
        Log.d(TAG, "onboarding_vm $isActive")
        viewModelScope.launch {
            Helper.putThemeStatus(context, THEME_KEY, isActive)
        }
    }
}
