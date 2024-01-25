package com.example.tokopaerbe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.tokopaerbe.helper.Constant.LANGUAGE_EN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_IN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_KEY
import com.example.tokopaerbe.helper.Helper

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

        val language = Helper.getLanguageStatus(this, LANGUAGE_KEY)
        if (language.equals(LANGUAGE_IN, true)) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(LANGUAGE_IN))
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(LANGUAGE_EN))
        }
    }
}