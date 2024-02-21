package com.example.tokopaerbe.view.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentHomeBinding
import com.example.tokopaerbe.helper.Constant.LANGUAGE_EN
import com.example.tokopaerbe.helper.Constant.LANGUAGE_IN
import com.example.tokopaerbe.helper.checkIf
import com.example.tokopaerbe.viewmodel.DashBoardViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding,DashBoardViewModel >(FragmentHomeBinding::inflate) {
    override val viewModel: DashBoardViewModel by viewModel()
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun observeData() {
        viewModel.run {
            theme.launchAndCollectIn(viewLifecycleOwner) {
                binding.switchTheme.checkIf(it)
            }
        }
    }

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
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(LANGUAGE_IN))
                    lang = LANGUAGE_IN
                }

                false -> {
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(LANGUAGE_EN))
                    lang = LANGUAGE_EN
                }
            }
            context?.let { viewModel.putLanguageStatus(lang) }
            analytics(lang)
        }
        //Button Log Out
        binding.buttonLogout.setOnClickListener {
            viewModel.clearAllSession()
            activity?.supportFragmentManager?.findFragmentById(R.id.fragment_container)?.findNavController()?.navigate(R.id.action_dashboardFragment_to_loginFragment)
//            throw RuntimeException("Error AJA ")
        }
    }

    override fun initView() {
        binding.buttonLogout.text = getString(R.string.logout)
        binding.tvLight.text = getString(R.string.light)
        binding.tvDark.text = getString(R.string.dark)

        viewModel.getThemeStatus()
    }

    private fun analytics(data: String) {
        val bundle = Bundle()
        bundle.putString("show_message", data)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun onResume() {
        super.onResume()
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle().apply { putString("screenName", getString(R.string.register)) })
    }
}