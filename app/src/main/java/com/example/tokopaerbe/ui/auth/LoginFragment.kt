package com.example.tokopaerbe.ui.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.helper.TextWatcherConfigure
import com.example.tokopaerbe.ui.dashboard.DashboardFragment
import com.example.tokopaerbe.databinding.FragmentLoginBinding
import com.example.tokopaerbe.helper.SnK


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        tColor()
    }

    private fun setOnClickListener() {
        binding.let {
            it.buttonLogin.setOnClickListener{
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            }
            it.buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            it.emailEditText.addTextChangedListener(TextWatcherConfigure(1) {
                    email -> validateEmail(email)
            })
            it.passwordEditText.addTextChangedListener(TextWatcherConfigure(1) {
                    password -> isValidPassword(password)
            })
        }
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
    fun tColor() {
        val sk = binding.syaratKetentuan
        sk.text = SnK.applyCustomTextColor(
            requireContext(),
            resources.getString(R.string.term_condition_login)
        )
        sk.movementMethod = LinkMovementMethod.getInstance()
    }

}

