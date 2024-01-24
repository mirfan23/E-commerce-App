package com.example.tokopaerbe.ui.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.helper.TextWatcherConfigure
import com.example.tokopaerbe.databinding.FragmentRegisterBinding
import com.example.tokopaerbe.helper.SnK

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        tColor()
        initView()
    }

    private fun initView() {
        binding.toolbar.title = getString(R.string.profile)
        binding.emailEditText.hint = getString(R.string.email)
        binding.emailTextInputLayout.helperText = getString(R.string.example_email)
        binding.passowrdEditText.hint = getString(R.string.password)
        binding.passwordTextInputLayout.helperText = getString(R.string.minimum_character)
        binding.buttonRegister.text = getString(R.string.register)
        binding.another.text = getString(R.string.another)
        binding.buttonLogin.text = getString(R.string.login)

    }

    private fun setOnClickListener() {
        binding.let {
            it.buttonLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            it.buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
            }
            it.passowrdEditText.addTextChangedListener(TextWatcherConfigure(1) {
                    password -> isValidPassword(password)
            })

        }
    }
    private fun isValidPassword(password: String): Boolean {
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
        binding.passwordTextInputLayout.isErrorEnabled = true
        binding.passwordTextInputLayout.error = errorMessage
    }

    private fun clearError() {
        binding.passwordTextInputLayout.isErrorEnabled = false
    }


    fun tColor() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}
