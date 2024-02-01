package com.example.tokopaerbe.view.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.UiState
import com.example.core.remote.data.LoginRequest
import com.example.tokopaerbe.R
import com.example.tokopaerbe.helper.TextWatcherConfigure
import com.example.tokopaerbe.databinding.FragmentLoginBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SnK
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<FragmentLoginBinding, PreLoginViewModel>(FragmentLoginBinding::inflate) {
    override val viewModel: PreLoginViewModel by viewModel()

    override fun initView() = with(binding) {

        toolbar.title = getString(R.string.login)
        buttonLogin.text = getString(R.string.login)
        another.text = getString(R.string.another2)
        buttonRegister.text = getString(R.string.register)
        emailEditText.hint = getString(R.string.email)
        passwordEditText.hint = getString(R.string.password)
        termsCo()
    }

    override fun initListener() = with(binding) {
        let {
            it.buttonLogin.setOnClickListener {
                val email = binding.emailEditText.text.toString().trim()
                val password = binding.passwordEditText.text.toString().trim()

                if (validateEmail(email) && isValidPassword(password)) {
                    val request = LoginRequest(
                        email = email,
                        password = password,
                        firebaseToken = ""
                    )
                    viewModel.fetchLogin(request)
                }
            }
            it.buttonRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            it.emailEditText.addTextChangedListener(TextWatcherConfigure(1) { email ->
                validateEmail(email)
            })
            it.passwordEditText.addTextChangedListener(TextWatcherConfigure(1) { password ->
                isValidPassword(password)
            })
        }
    }

    override fun observeData() {
        with(viewModel) {
            lifecycleScope.launch {
                responseLogin.collectLatest { loginState ->
                    when (loginState) {
                        is UiState.Success -> {
                            val loginResponse = loginState.data
                            if (loginResponse.accessToken.isNotEmpty()) {
                                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                            }
                        }

                        is UiState.Error -> {
                            val errorMessage = "error: ${loginState.error}"
                            context?.let {
                                CustomSnackbar.showSnackBar(
                                    it,
                                    binding.root,
                                    errorMessage
                                )
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS

        if (emailPattern.matcher(email).matches() || email.length <= 2) {
            binding.emailTextInputLayout.isErrorEnabled = false
        } else {
            binding.emailTextInputLayout.error = getString(R.string.inValidEmail)
        }
        return true
    }

    private fun isValidPassword(password: String): Boolean {
        val minLength = 8
        if (password.length < minLength) {
            showError("Password Minimal $minLength")
            return true
        }
        /**
         * akan digunakan kembali
         */
//        if (password.none { it.isUpperCase() }) {
//            showError("Password setidaknya terdapat satu huruf kapital")
//            return false
//        }
//        if (password.none { !it.isLetterOrDigit() }) {
//            showError("Password harus mengandung setidaknya satu karakter khusus")
//            return false
//        }
        clearError()
        return true
    }

    private fun showError(errorMessage: String) {
        binding.passwordtextInputLayout.isErrorEnabled = true
        binding.passwordtextInputLayout.error = errorMessage
    }

    private fun clearError() {
        binding.passwordtextInputLayout.isErrorEnabled = false
    }

    private fun termsCo() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        println("")
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}

