package com.example.tokopaerbe.preLogin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.tokopaerbe.MainActivity
import com.example.tokopaerbe.R
import com.example.tokopaerbe.auth.LoginFragment
import com.example.tokopaerbe.databinding.FragmentSplashScreenBinding


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

        rotateVIew()
    }

    private fun rotateVIew() {
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
            0f, -70f
        )
        val greenAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenGreen,
            View.TRANSLATION_Y,
            0f, -150f
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

        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                navigateToNextFragment()
            }
        })
    }

    private fun navigateToNextFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val sharedPreference = (activity as MainActivity).getSharedPreference()
        val isOnBoardingShown = sharedPreference.getBoolean("onboarding_completed", false)
        val nextFragment = if (isOnBoardingShown) LoginFragment() else OnBoardingFragment()
//        val nextFragment = OnBoardingFragment()
        fragmentTransaction.replace(R.id.fragment_container, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}