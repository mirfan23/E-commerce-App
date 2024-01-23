package com.example.tokopaerbe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import com.example.tokopaerbe.helper.Helper
import com.example.tokopaerbe.ui.dashboard.HomeFragment.Companion.LANGUAGE_EN
import com.example.tokopaerbe.ui.dashboard.HomeFragment.Companion.LANGUAGE_IN
import com.example.tokopaerbe.ui.dashboard.HomeFragment.Companion.LANGUAGE_KEY

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val themeChecker = Helper.getThemeStatus(this, "dark")
        if (themeChecker) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
