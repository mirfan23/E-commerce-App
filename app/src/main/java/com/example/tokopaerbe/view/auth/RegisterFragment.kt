package com.example.tokopaerbe.view.auth

import android.text.method.LinkMovementMethod
import android.util.Patterns
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.core.domain.model.oError
import com.example.core.domain.model.onSuccess
import com.example.tokopaerbe.R
import com.example.core.remote.data.RegisterRequest
import com.example.tokopaerbe.databinding.FragmentRegisterBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SnK
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, PreLoginViewModel>(FragmentRegisterBinding::inflate) {
    override val viewModel: PreLoginViewModel by viewModel()
    override fun initView() {
        binding.toolbar.title = getString(R.string.register)
        binding.emailEditText.hint = getString(R.string.email)
        binding.emailTextInputLayout.helperText = getString(R.string.example_email)
        binding.passowrdEditText.hint = getString(R.string.password)
        binding.passwordTextInputLayout.helperText = getString(R.string.minimum_character)
        binding.buttonRegister.text = getString(R.string.register)
        binding.another.text = getString(R.string.another)
        binding.buttonLogin.text = getString(R.string.login)
        termsCo()
    }

    override fun initListener() {
        binding.let {
            /**
             * Button for Register to Login
             */
            it.buttonLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            /**
             * Button for Register to Profile
             */

            it.buttonRegister.setOnClickListener {
                val email = binding.emailEditText.text.toString().trim()
                val password = binding.passowrdEditText.text.toString().trim()

                if (isValidEmail(email) && isValidPassword(password)) {
                    val request = RegisterRequest(
                        email = email,
                        password = password,
                        firebaseToken = ""
                    )
                    viewModel.fetchRegister(request)
                } else {
                    context?.let { it1 ->
                        CustomSnackbar.showSnackBar(
                            it1,
                            binding.root,
                            getString(R.string.register_validation)
                        )
                    }
                }
            }
            /**
             * Button for Password Check
             * akan digunakan kembali
             */
//            it.passowrdEditText.addTextChangedListener(TextWatcherConfigure(1) { password ->
//                isValidPassword(password)
//            })

        }
    }

    override fun observeData() {
        with(viewModel) {
            lifecycleScope.launch {
                response.collectLatest { registerState ->
                    registerState.onSuccess { token ->
                        viewModel.saveSession(token)
                        findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
                    }.oError { error ->
                        val errorMessage = "error: ${error}"
                        context?.let {
                            CustomSnackbar.showSnackBar(
                                it,
                                binding.root,
                                errorMessage
                            )
                        }
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS

        if (emailPattern.matcher(email).matches() || email.length <= 2) {
            binding.emailTextInputLayout.isErrorEnabled = true
        } else {
            binding.emailTextInputLayout.error = getString(R.string.inValidEmail)
            return false
        }
        return emailPattern.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val minLength = 8
        if (password.length < minLength) {
            showError("Password Minimal $minLength")
            return true
        }
        /** akan dipakai kembali
         * if (password.none { it.isUpperCase() }) {
         *             showError("Password setidaknya terdapat satu huruf kapital")
         *             return false
         *         }
         *         if (password.none { !it.isLetterOrDigit() }) {
         *             showError("Password harus mengandung setidaknya satu karakter khusus")
         *             return false
         *         }
         */

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


    fun termsCo() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}
