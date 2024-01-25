package com.example.tokopaerbe.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentHomeBinding
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

        initView()

        binding.buttonLogout.setOnClickListener {}

        val themeChecker = Helper.getThemeStatus(requireContext(), "dark")
        binding.switchTheme.checkIf(themeChecker)

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
    }

    private fun initView() {
        binding.buttonLogout.text = getString(R.string.logout)
        binding.tvLight.text = getString(R.string.light)
        binding.tvDark.text = getString(R.string.dark)
    }

    companion object {
        const val LANGUAGE_KEY = "language_key"
        const val LANGUAGE_IN = "in"
        const val LANGUAGE_EN = "en"
    }
}