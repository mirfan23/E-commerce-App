package com.example.tokopaerbe.view.preLogin

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.core.domain.state.SplashState
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentSplashScreenBinding
import com.example.tokopaerbe.helper.Constant.ANIMATION_DELAY
import com.example.tokopaerbe.helper.Constant.ANIMATION_START
import com.example.tokopaerbe.helper.Constant.GREEN_TRANSLATION_Y
import com.example.tokopaerbe.helper.Constant.RED_ROTATION
import com.example.tokopaerbe.helper.Constant.RED_TRANSLATION_X
import com.example.tokopaerbe.helper.Constant.RED_TRANSLATION_Y
import com.example.tokopaerbe.helper.Constant.YELLOW_ROTATION
import com.example.tokopaerbe.helper.Constant.YELLOW_TRANSLATION_X
import com.example.tokopaerbe.helper.Constant.YELLOW_TRANSLATION_Y
import com.example.tokopaerbe.viewmodel.DashBoardViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding, DashBoardViewModel>(FragmentSplashScreenBinding::inflate) {
    override val viewModel: DashBoardViewModel by viewModel()
    override fun initView() {
        viewModel.getOnBoardingState()
        animation()
        lifecycleScope.launch {
            delay(ANIMATION_DELAY)
        }
    }

    override fun initListener() {}

    override fun observeData() {
        with(viewModel) {
            onBoarding.launchAndCollectIn(viewLifecycleOwner) { state ->
                Handler(Looper.getMainLooper()).postDelayed({
                    val navController =
                        activity?.supportFragmentManager?.findFragmentById(R.id.fragment_container)
                            ?.findNavController()
                    when (state) {
                        is SplashState.OnBoarding -> {
                            navController?.navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
                        }
                        is SplashState.Dashboard -> {
                            navController?.navigate(R.id.action_splashScreenFragment_to_dashboardFragment)
                        }
                        is SplashState.Profile -> {
                            navController?.navigate(R.id.action_splashScreenFragment_to_profileFragment)
                        }
                        else -> {
                            navController?.navigate(R.id.action_splashScreenFragment_to_loginFragment)
                        }
                    }
                }, 3000L)

            }
        }

    }

    private fun animation() {
        val yellowAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenYellow,
            View.ROTATION,
            ANIMATION_START,
            YELLOW_ROTATION
        )
        val redAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenRed,
            View.ROTATION,
            ANIMATION_START,
            RED_ROTATION,
        )
        val redAnimatorTrans = ObjectAnimator.ofFloat(
            binding.splashScreenRed,
            View.TRANSLATION_X,
            ANIMATION_START,
            RED_TRANSLATION_X
        )
        val yellowAnimatorTrans = ObjectAnimator.ofFloat(
            binding.splashScreenYellow,
            View.TRANSLATION_X,
            ANIMATION_START,
            YELLOW_TRANSLATION_X
        )
        val redAnimatorTrans2 = ObjectAnimator.ofFloat(
            binding.splashScreenRed,
            View.TRANSLATION_Y,
            ANIMATION_START,
            RED_TRANSLATION_Y
        )
        val yellowAnimatorTrans2 = ObjectAnimator.ofFloat(
            binding.splashScreenYellow,
            View.TRANSLATION_Y,
            ANIMATION_START,
            YELLOW_TRANSLATION_Y
        )
        val greenAnimator = ObjectAnimator.ofFloat(
            binding.splashScreenGreen,
            View.TRANSLATION_Y,
            ANIMATION_START,
            GREEN_TRANSLATION_Y
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
        animatorSet.duration = ANIMATION_DELAY
        animatorSet.start()
    }

//    private fun navigate() {
//        val skip = context?.let { viewModel.getOnBoardingState() }
//
//        if (skip == true) {
//            Handler(Looper.getMainLooper()).postDelayed(
//                { findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment) },
//                ANIMATION_DELAY
//            )
//        } else {
//            Handler(Looper.getMainLooper()).postDelayed(
//                { findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment) },
//                ANIMATION_DELAY
//            )
//        }
//    }
}