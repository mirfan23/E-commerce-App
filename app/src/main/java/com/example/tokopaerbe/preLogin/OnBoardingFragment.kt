package com.example.tokopaerbe.preLogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.tokopaerbe.R
import com.example.tokopaerbe.adapter.OnboardingAdapter
import com.example.tokopaerbe.databinding.FragmentOnBoardingBinding
import com.example.tokopaerbe.helper.Helper
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {
    private lateinit var _binding: FragmentOnBoardingBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewpager
        val onboardingAdapter = OnboardingAdapter()
        viewPager.adapter = onboardingAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
        context?.let { Helper.putObstatus(it, SKIP_VALUE, true) }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.buttonNext.isVisible = position != onboardingAdapter.itemCount - 1
            }
        })

        binding.buttonJoin.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_registerFragment)
        }
        binding.buttonNext.setOnClickListener {
            val currentTabIndex = tabLayout.selectedTabPosition
            val nextTabIndex = currentTabIndex + 1
            if (nextTabIndex < onboardingAdapter.itemCount) {
                viewPager.currentItem = nextTabIndex
            }
        }
        binding.buttonSkip.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }
    }

    companion object {
        private const val SKIP_VALUE = "skip"
    }
}

