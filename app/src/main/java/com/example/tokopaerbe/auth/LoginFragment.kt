package com.example.tokopaerbe.auth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.tokopaerbe.R
import com.example.tokopaerbe.TextWatcherConfigure
import com.example.tokopaerbe.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.let {
            it.buttonLogin.setOnClickListener{}
            it.buttonRegister.setOnClickListener {
                navigateToRegister()
            }
            it.emailEditText.addTextChangedListener(TextWatcherConfigure(1) {
                email -> validateEmail(email)
            })
            it.passwordEditText.addTextChangedListener(TextWatcherConfigure(1) {
                password -> isValidPassword(password)
            })
        }
    }

    private fun navigateToRegister() {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val nextFragment = RegisterFragment()

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

    private fun isValidPassword(password: String): Boolean {
        println("irfan : $password")
        val minLength = 8
        if (password.length < minLength) {
            showError("Password Minimal $minLength")
            return true
        }
        if (password.none { it.isUpperCase() }) {
            showError("Password setidaknya terdapat satu huruf kapital")
            return false
        }
        if (password.none { !it.isLetterOrDigit() }) {
            showError("Password harus mengandung setidaknya satu karakter khusus")
            return false
        }
        clearError()
        return true
    }

    private fun showError(errorMessage: String) {
        binding.passwordtextInputLayout.isErrorEnabled = true
        binding.passwordtextInputLayout.error = errorMessage
        println("Error: $errorMessage")
    }

    private fun clearError() {
        binding.passwordtextInputLayout.isErrorEnabled = false
        println("Password valid")
    }



    companion object {

    }
}