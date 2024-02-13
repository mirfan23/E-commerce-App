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
import com.example.tokopaerbe.R
import com.example.core.remote.data.RegisterRequest
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.databinding.FragmentRegisterBinding
import com.example.tokopaerbe.helper.CustomSnackbar
import com.example.tokopaerbe.helper.SnK
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.HttpException

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

    override fun initListener() = with(binding) {
        emailEditText.doOnTextChanged { text, _, before, _ ->
            viewModel.validateRegisterEmail(text.toString())
        }
        passowrdEditText.doOnTextChanged { text, _, before, _ ->
            viewModel.validateRegisterPassword(text.toString())
        }
        buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        buttonRegister.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passowrdEditText.text.toString().trim()

            if (emailTextInputLayout.isErrorEnabled.not() && passwordTextInputLayout.isErrorEnabled.not()) {
                viewModel.validateRegisterField(email, password)
            }
        }
    }

    override fun observeData() {
        with(viewModel) {
            response.launchAndCollectIn(viewLifecycleOwner) { registerState ->
                registerState.onSuccess { token ->
                    viewModel.saveSession(token)
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
                }.oError { error ->
                    binding.loadingOverlay.visibility = View.GONE
                    binding.lottieLoading.visibility = View.GONE
                    val errorMessage = when (error) {
                        is HttpException -> {
                            val errorBody = error.response()?.errorBody()?.string()
                            "$errorBody"
                        }
                        else -> "${error.message}"
                    }
                    context?.let {
                        CustomSnackbar.showSnackBar(
                            it,
                            binding.root,
                            errorMessage
                        )
                    }
                }.onLoading {
                    binding.loadingOverlay.visibility = View.VISIBLE
                    binding.lottieLoading.visibility = View.VISIBLE
                }
                resetValidateRegisterField()
            }
            validateRegisterEmail.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { isValid ->
                        binding.run {
                            emailTextInputLayout.isErrorEnabled = isValid.not()
                            if (isValid) {
                                emailTextInputLayout.error = null
                            } else emailTextInputLayout.error = "Email is invalid"
                        }
                    }
            }
            validateRegisterPassword.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { isValid ->
                        binding.run {
                            passwordTextInputLayout.isErrorEnabled = isValid.not()
                            if (isValid) {
                                passwordTextInputLayout.error = null
                            } else passwordTextInputLayout.error = "Password is invalid"
                        }
                    }
            }
            validateRegisterField.launchAndCollectIn(viewLifecycleOwner) { state ->
                state.onCreated {}
                    .onValue { isPass ->
                        binding.run {
                            emailTextInputLayout.isErrorEnabled = isPass.not()
                            passwordTextInputLayout.isErrorEnabled = isPass.not()
                            if (isPass) {
                                val email = binding.emailEditText.text.toString().trim()
                                val password = binding.passowrdEditText.text.toString().trim()
                                val request = RegisterRequest(
                                    email = email,
                                    password = password,
                                    firebaseToken = ""
                                )
                                viewModel.fetchRegister(request)
                            } else {
                                emailTextInputLayout.error = "email is required"
                                emailTextInputLayout.error = "password is required"
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

    fun termsCo() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}

