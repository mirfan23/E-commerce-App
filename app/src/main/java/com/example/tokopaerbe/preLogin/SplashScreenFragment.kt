package com.example.tokopaerbe.preLogin

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
import com.example.tokopaerbe.helper.Helper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding

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
            delay(1000)
            navigate()
        }
    }

    private fun animation() {
        val yellowAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenYellow,
            View.ROTATION,
            0f, -25f
        )
        val redAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenRed,
            View.ROTATION,
            0f, 25f,
        )
        val redAnimatorTrans = ObjectAnimator.ofFloat(
            binding.splashScreenRed,
            View.TRANSLATION_X,
            0f, 75f
        )
        val yellowAnimatorTrans = ObjectAnimator.ofFloat(
            binding.splashScreenYellow,
            View.TRANSLATION_X,
            0f, -75f
        )
        val redAnimatorTrans2 = ObjectAnimator.ofFloat(
            binding.splashScreenRed,
            View.TRANSLATION_Y,
            0f, -70f
        )
        val yellowAnimatorTrans2 = ObjectAnimator.ofFloat(
            binding.splashScreenYellow,
            View.TRANSLATION_Y,
            0f, -90f
        )
        val greenAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenGreen,
            View.TRANSLATION_Y,
            0f, -170f
        )

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            yellowAnimator,
            yellowAnimatorTrans,
            yellowAnimatorTrans2,
            redAnimator,
            redAnimatorTrans,
            redAnimatorTrans2,
            greenAnimator
        )
        animatorSet.duration = 1000
        animatorSet.start()
    }

    private fun navigate() {
        val skip = context?.let { Helper.getObstatus(it, "skip") }

        if (skip == true) {
            Handler(Looper.getMainLooper()).postDelayed(
                { findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment) },
                1000
            )
        } else {
            Handler(Looper.getMainLooper()).postDelayed(
                { findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment) },
                1000
            )
        }
    }

}