package com.example.tokopaerbe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.helper.Constant.LANGUAGE_EN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_IN
import com.example.tokopaerbe.viewmodel.DashBoardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: DashBoardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getThemeStatus()
        viewModel.theme.launchAndCollectIn(this) {
            AppCompatDelegate.setDefaultNightMode(if(it) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        }

        val language = viewModel.getLanguageStatus()
        if (language) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(LANGUAGE_IN))
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(LANGUAGE_EN))
        }
    }
}