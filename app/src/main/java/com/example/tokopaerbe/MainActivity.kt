package com.example.tokopaerbe

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tokopaerbe.auth.LoginFragment
import com.example.tokopaerbe.databinding.ActivityMainBinding
import com.example.tokopaerbe.preLogin.OnBoardingFragment
import com.example.tokopaerbe.preLogin.SplashScreenFragment

class MainActivity : AppCompatActivity(), OnboardingListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val onBoardingCompleted = sharedPreferences.getBoolean("onboarding_complete", false)

        if (onBoardingCompleted) {
            replaceFragment(LoginFragment())
        } else {
            replaceFragment(OnBoardingFragment())
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, SplashScreenFragment())
        fragmentTransaction.commit()
    }

    fun getSharedPreference() = sharedPreferences

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun markOnboardingComplete() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("onboarding_completed", true)
        editor.apply()
        replaceFragment(LoginFragment())
    }

}
