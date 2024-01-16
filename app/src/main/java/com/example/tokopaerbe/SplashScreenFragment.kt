package com.example.tokopaerbe

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ObjectAnimator.ofFloat
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.tokopaerbe.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            animatedView(constraintContainer, ALPHA_ANIMATION, 0f, 1f, 1000L)
            animatedView(splashScreenLogo, FADE_ANIMATION, (0.5).toFloat(), 1f, 800L)
            animatedView(splashScreenYellow, TRANSLATION_ANIMATION, 0f, -55f, 1000L)
            animatedView(splashScreenYellow, ROTATE_ANIMATION, 0f, -20f, 1000L)
            animatedView(splashScreenRed, TRANSLATION_ANIMATION, 0f, 50f, 1000L)
            animatedView(splashScreenRed, ROTATE_ANIMATION, 0f, 20f, 1000L)
            animatedView(splashScreenGreen, "margin_anim", 0f, 200f, 1000L)
        }
    }

    private fun animatedView(
        view: View,
        propertyName: String,
        startValue: Float,
        endValue: Float,
        durationAnimation: Long
    ) {
        val animator = ValueAnimator.ofFloat(startValue, endValue)
        animator.duration = durationAnimation
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            view.run {
                when {
                    propertyName.equals(ROTATE_ANIMATION, true) -> {
                        rotation = animatedValue
                    }

                    propertyName.equals(TRANSLATION_ANIMATION, true) -> {
                        translationX = animatedValue
                    }

                    propertyName.equals(ALPHA_ANIMATION, true) -> {
                        scaleX = animatedValue
                    }

                    propertyName.equals(FADE_ANIMATION, true) -> {
                        alpha = animatedValue
                    }

                    else -> {
                        val lp = layoutParams as ConstraintLayout.LayoutParams
                        lp.setMargins(0, 0, 0, animatedValue.toInt())
                        layoutParams = lp
                        requestLayout()
                    }
                }
            }

        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                navigateToNextFragment()
            }
        })

        animator.start()
    }

    private fun navigateToNextFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val nextFragment = OnBoardingFragment()

        fragmentTransaction.replace(R.id.fragment_container, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    companion object {
        const val ALPHA_ANIMATION = "alpha_animation_key"
        const val ROTATE_ANIMATION = "rotate_animation_key"
        const val TRANSLATION_ANIMATION = "translation_animation_key"
        const val FADE_ANIMATION = "fade_animation_key"
    }
}

//class SplashScreenFragment : Fragment() {
//    private lateinit var binding: FragmentSplashScreenBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        rotateVIew()
//    }
//
//    private fun rotateVIew() {
//        val yellowAnimator = ObjectAnimator.ofFloat(
//            binding.splashScreenYellow,
//            View.ROTATION,
//            0f, -25f
//        )
//        val redAnimator = ObjectAnimator.ofFloat(
//            binding.splashScreenRed,
//            View.ROTATION,
//            0f, 25f,
//        )
//        val greenAnimator = ObjectAnimator.ofFloat(
//            binding.splashScreenGreen,
//            View.TRANSLATION_Y,
//            0f, -150f
//        )
//
//        yellowAnimator.duration = 1000L
//        redAnimator.duration = 1000
//        greenAnimator.duration = 1000
//
//        yellowAnimator.start()
//        redAnimator.start()
//        greenAnimator.start()
//    }
//}