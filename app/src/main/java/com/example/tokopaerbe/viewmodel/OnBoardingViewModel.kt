package com.example.tokopaerbe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tokopaerbe.auth.RegisterFragment

class OnBoardingViewModel: ViewModel() {

    private val _currentViewPagerPosition = MutableLiveData<Int>()
    val currentViewPagerPosition: LiveData<Int>
        get() = _currentViewPagerPosition

    init {
        _currentViewPagerPosition.value = 0
    }

    private val _navigateToRegister = MutableLiveData<Boolean>()
    val navigateToRegister: LiveData<Boolean>
        get() = _navigateToRegister

    private  val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    fun onJoinNowClicked() {
        _navigateToRegister.value =true
    }
    fun onSkipClicked() {
        _navigateToLogin.value = true
    }
    fun onNextClicked(){
//        val currentTabIndex = tabLayout.selectedTabPosition
//        val nextTabIndex = currentTabIndex +1
//        if (nextTabIndex < onboardingAdapter.itemCount) {
//            viewPager.currentItem = nextTabIndex
//        }
    }

    fun onNavigationToRegisterComplete() {
        _navigateToRegister.value = false
    }

    fun onNavigationToLoginComplete() {
        _navigateToLogin.value = false
    }

    companion object {
        private const val MAX_VIEW_PAGER_POSITION = 2 // Ganti dengan jumlah total tab - 1
    }
}