package com.example.tokopaerbe.auth

import android.os.Bundle
import android.util.Patterns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.tokopaerbe.R
import com.example.tokopaerbe.TextWatcherConfigure
import com.example.tokopaerbe.databinding.FragmentRegisterBinding
import java.util.regex.Pattern


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.let {
            it.buttonLogin.setOnClickListener {
                navigateToLogin()
            }
            it.buttonRegister.setOnClickListener {
                navigateToProfile()
            }

        }
    }
    private fun navigateToLogin() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val nextFragment = LoginFragment()

        fragmentTransaction.replace(R.id.fragment_container, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun navigateToProfile() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val nextFragment = ProfileFragment()

        fragmentTransaction.replace(R.id.fragment_container, nextFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun validateEmail(email: String) {
        val emailPattern = Patterns.EMAIL_ADDRESS

        if (emailPattern.matcher(email).matches() || email.length <= 2) {
            binding.emailTextInputLayout.isErrorEnabled = false
        } else {
            binding.emailTextInputLayout.error = "email tidak valid"
        }
    }



    companion object {
    }
}