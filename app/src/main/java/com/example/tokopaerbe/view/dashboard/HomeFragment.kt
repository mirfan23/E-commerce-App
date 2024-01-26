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
import com.example.tokopaerbe.helper.Helper
import com.example.tokopaerbe.helper.checkIf

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themeChecker = Helper.getThemeStatus(requireContext(), "dark")
        val isLanguageIN = context?.let { Helper.getLanguageStatus(it, LANGUAGE_KEY) }
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
                    context?.let { Helper.putThemeStatus(it, "dark", true) }
                }

                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    context?.let { Helper.putThemeStatus(it, "dark", false) }
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
            context?.let { Helper.putLanguageStatus(it, lang, LANGUAGE_KEY) }
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