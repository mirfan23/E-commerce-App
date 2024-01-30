package com.example.tokopaerbe.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentHomeBinding
import com.example.tokopaerbe.helper.Constant.LANGUAGE_EN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_IN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_KEY
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.checkIf
import com.example.tokopaerbe.viewmodel.SharedPreferencesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: SharedPreferencesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themeChecker = viewModel.getThemeStatus()
        val isLanguageIN = context?.let { viewModel.getLanguageStatus() }
        binding.switchTheme.checkIf(themeChecker)
        binding.switchLanguage.checkIf(isLanguageIN == LANGUAGE_IN)
        initView()
        initListener()
    }

    private fun initListener() {
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
            context?.let { it1 -> CustomSnackbar.showSnackBar(it1, binding.root, "ini Snack Bar") }
        }
    }

    private fun initView() {
        binding.buttonLogout.text = getString(R.string.logout)
        binding.tvLight.text = getString(R.string.light)
        binding.tvDark.text = getString(R.string.dark)

    }
}