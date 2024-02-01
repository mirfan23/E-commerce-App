package com.example.tokopaerbe.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentHomeBinding
import com.example.tokopaerbe.helper.Constant.LANGUAGE_EN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_IN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_KEY
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.checkIf
import com.example.tokopaerbe.viewmodel.SharedPreferencesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding,SharedPreferencesViewModel >(FragmentHomeBinding::inflate) {
    override val viewModel: SharedPreferencesViewModel by viewModel()

    override fun observeData() {}

    override fun initListener() {
        //Button Switch Theme for Change Theme
        binding.switchTheme.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    context?.let { viewModel.putThemeStatus(true) }
                }

                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    context?.let { viewModel.putThemeStatus(false) }
                }
            }
        }
        //Button Switch Language for Change Language
        binding.switchLanguage.setOnCheckedChangeListener { _, isChecked ->
            val lang: String
            when (isChecked) {
                true -> {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(LANGUAGE_IN)
                    )
                    lang = LANGUAGE_IN
                }

                false -> {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(
                            LANGUAGE_EN
                        )
                    )
                    lang = LANGUAGE_EN
                }
            }
            context?.let { viewModel.putLanguageStatus(lang) }
        }
        //Button Log Out
        binding.buttonLogout.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
        }
    }

    override fun initView() {
        binding.buttonLogout.text = getString(R.string.logout)
        binding.tvLight.text = getString(R.string.light)
        binding.tvDark.text = getString(R.string.dark)

    }
}