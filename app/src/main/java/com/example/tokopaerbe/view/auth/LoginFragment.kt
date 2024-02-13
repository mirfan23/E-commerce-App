package com.example.tokopaerbe.view.auth

import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.catnip.core.base.BaseFragment
import com.example.core.domain.state.oError
import com.example.core.domain.state.onCreated
import com.example.core.domain.state.onLoading
import com.example.core.domain.state.onSuccess
import com.example.core.domain.state.onValue
import com.example.core.remote.data.LoginRequest
import com.example.core.utils.DataMapper.toDataToken
import com.example.core.utils.DataMapper.toProfileName
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentLoginBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SnK
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

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
        emailEditText.doOnTextChanged { text, _, before, _ ->
            viewModel.validateLoginEmail(text.toString())
            enableLoginButtonIfValid()
        }
        passwordEditText.doOnTextChanged { text, _, before, _ ->
            viewModel.validateLoginPassword(text.toString())
            enableLoginButtonIfValid()
        }
        buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        buttonLogin.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (emailTextInputLayout.isErrorEnabled.not() && passwordtextInputLayout.isErrorEnabled.not()) {
                viewModel.validateLoginField(email, password)
                println("MASUK: CLICK COY")
                println("MASUK: $email $password")
            }
        }
    }

    override fun observeData() {
        with(viewModel) {
            responseLogin.launchAndCollectIn(viewLifecycleOwner) { loginState ->
                loginState.onSuccess { data ->
                    saveProfileName(data.toProfileName())
                    saveSession(data.toDataToken())
                    setLoadingState(false)
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                }.oError { error ->
                    setLoadingState(false)
                    val errorMessage = when (error) {
                        is HttpException -> {
                            val errorBody = error.response()?.errorBody()?.string()
                            "$errorBody"
                        }
                        else -> "${error.message}"
                    }
                    println("MASUK: $errorMessage")
                    context?.let {
                        CustomSnackbar.showSnackBar(
                            it,
                            binding.root,
                            errorMessage
                        )
                    }
                }.onLoading {
                    setLoadingState(true)
                }
                resetValidateLoginField()
            }
            validateLoginEmail.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { isValid ->
                        binding.run {
                            emailTextInputLayout.isErrorEnabled = isValid.not()
                            if (isValid) {
                                emailTextInputLayout.error = null
                            } else emailTextInputLayout.error = "Email is required"
                        }
                    }
            }
            validateLoginPassword.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { isValid ->
                        binding.run {
                            passwordtextInputLayout.isErrorEnabled = isValid.not()
                            if (isValid) {
                                passwordtextInputLayout.error = null
                            } else passwordtextInputLayout.error = "Password is required"
                        }
                    }
            }
            validateLoginField.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { isPass ->
                        binding.run {
                            emailTextInputLayout.isErrorEnabled = isPass.not()
                            passwordtextInputLayout.isErrorEnabled = isPass.not()
                            if (isPass) {
                                val email = binding.emailEditText.text.toString().trim()
                                val password = binding.passwordEditText.text.toString().trim()
                                val request = LoginRequest(
                                    email = email,
                                    password = password,
                                    firebaseToken = ""
                                )
                                viewModel.fetchLogin(request)
                            } else {
                                emailTextInputLayout.error = "email is required"
                                emailTextInputLayout.error = "Password is required"
                                context?.let { it1 ->
                                    CustomSnackbar.showSnackBar(
                                        it1,
                                        binding.root,
                                        getString(R.string.register_validation)
                                    )
                                }
                            }
                        }
                    }
            }
        }
    }

    private fun termsCo() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun enableLoginButtonIfValid() {
        binding.buttonLogin.isEnabled =
            binding.emailTextInputLayout.error.isNullOrEmpty() && binding.passwordtextInputLayout.error.isNullOrEmpty()
    }
    private fun setLoadingState(loading: Boolean) {
        with(binding) {
            loadingOverlay.visibility = if (loading) View.VISIBLE else View.GONE
            lottieLoading.visibility = if (loading) View.VISIBLE else View.GONE
            buttonLogin.isEnabled = loading
        }
    }
}

