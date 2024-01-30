package com.example.tokopaerbe.view.preLogin

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
import com.example.tokopaerbe.helper.Constant.SKIP_VALUE
import com.example.tokopaerbe.viewmodel.SharedPreferencesViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment : Fragment() {
    private lateinit var _binding: FragmentOnBoardingBinding
    private val binding get() = _binding
    private val viewModel: SharedPreferencesViewModel by viewModel()

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
        viewModel.putOnBoardingState(true)
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
        initView()
    }

    private fun initView() {
        binding.buttonJoin.text = getString(R.string.join_now)
        binding.buttonSkip.text = getString(R.string.skip)
        binding.buttonNext.text = getString(R.string.next)
    }


}

