package com.example.tokopaerbe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.tokopaerbe.adapter.OnboardingAdapter
import com.example.tokopaerbe.auth.LoginFragment
import com.example.tokopaerbe.auth.RegisterFragment
import com.example.tokopaerbe.databinding.FragmentOnBoardingBinding
import com.example.tokopaerbe.viewmodel.OnBoardingViewModel
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {
    private lateinit var _binding: FragmentOnBoardingBinding
    private val binding get() = _binding
    private lateinit var viewModel: OnBoardingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[OnBoardingViewModel::class.java]

        val viewPager = binding.viewpager
        val onboardingAdapter = OnboardingAdapter()
        viewPager.adapter = onboardingAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) {_,_ ->}.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.buttonNext.isVisible = position != onboardingAdapter.itemCount -1
            }
        })

        binding.buttonJoin.setOnClickListener(){
            viewModel.onJoinNowClicked()
        }
        binding.buttonNext.setOnClickListener() {
            val currentTabIndex = tabLayout.selectedTabPosition
            val nextTabIndex = currentTabIndex +1
            if (nextTabIndex < onboardingAdapter.itemCount) {
                viewPager.currentItem = nextTabIndex
            }
        }
        binding.buttonSkip.setOnClickListener() {
            viewModel.onSkipClicked()
        }

        viewModel.navigateToRegister.observe(viewLifecycleOwner) {shouldNavigate ->
            if (shouldNavigate) {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val nextFragment = RegisterFragment()

                fragmentTransaction.replace(R.id.fragment_container, nextFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                viewModel.onNavigationToRegisterComplete()
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) {shouldNavigate ->
            if (shouldNavigate) {
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                val nextFragment = LoginFragment()

                fragmentTransaction.replace(R.id.fragment_container, nextFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

                viewModel.onNavigationToLoginComplete()
            }
        }
    }
}