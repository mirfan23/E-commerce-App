package com.example.tokopaerbe.view.preLogin

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentSplashScreenBinding
import com.example.tokopaerbe.helper.Constant.ANIMATION_DELAY
import com.example.tokopaerbe.helper.Constant.ANIMATION_START
import com.example.tokopaerbe.helper.Constant.GREEN_TRANSLATION_Y
import com.example.tokopaerbe.helper.Constant.RED_ROTATION
import com.example.tokopaerbe.helper.Constant.RED_TRANSLATION_X
import com.example.tokopaerbe.helper.Constant.RED_TRANSLATION_Y
import com.example.tokopaerbe.helper.Constant.SKIP_KEY
import com.example.tokopaerbe.helper.Constant.YELLOW_ROTATION
import com.example.tokopaerbe.helper.Constant.YELLOW_TRANSLATION_X
import com.example.tokopaerbe.helper.Constant.YELLOW_TRANSLATION_Y
import com.example.tokopaerbe.viewmodel.SharedPreferencesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding
    private val viewModel: SharedPreferencesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animation()
        lifecycleScope.launch {
            delay(ANIMATION_DELAY)
            navigate()
        }
    }

    private fun animation() {
        val yellowAnimator = ObjectAnimator.ofFloat(binding.splashScreenYellow, View.ROTATION, ANIMATION_START, YELLOW_ROTATION)
        val redAnimator = ObjectAnimator.ofFloat(binding.splashScreenRed, View.ROTATION, ANIMATION_START, RED_ROTATION,)
        val redAnimatorTrans = ObjectAnimator.ofFloat(binding.splashScreenRed, View.TRANSLATION_X, ANIMATION_START, RED_TRANSLATION_X)
        val yellowAnimatorTrans = ObjectAnimator.ofFloat(binding.splashScreenYellow, View.TRANSLATION_X, ANIMATION_START, YELLOW_TRANSLATION_X)
        val redAnimatorTrans2 = ObjectAnimator.ofFloat(binding.splashScreenRed, View.TRANSLATION_Y, ANIMATION_START, RED_TRANSLATION_Y)
        val yellowAnimatorTrans2 = ObjectAnimator.ofFloat(binding.splashScreenYellow, View.TRANSLATION_Y, ANIMATION_START, YELLOW_TRANSLATION_Y)
        val greenAnimator = ObjectAnimator.ofFloat(binding.splashScreenGreen, View.TRANSLATION_Y, ANIMATION_START, GREEN_TRANSLATION_Y)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(yellowAnimator, yellowAnimatorTrans, yellowAnimatorTrans2, redAnimator, redAnimatorTrans, redAnimatorTrans2, greenAnimator)
        animatorSet.duration = ANIMATION_DELAY
        animatorSet.start()
    }

    private fun navigate() {
        val skip = context?.let { viewModel.getOnBoardingState() }

        if (skip == true) {
            Handler(Looper.getMainLooper()).postDelayed(
                { findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment) },
                ANIMATION_DELAY
            )
        } else {
            Handler(Looper.getMainLooper()).postDelayed(
                { findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment) },
                ANIMATION_DELAY
            )
        }
    }
}