package com.example.tokopaerbe.view.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.core.remote.service.RegisterRequest
import com.example.tokopaerbe.helper.TextWatcherConfigure
import com.example.tokopaerbe.databinding.FragmentRegisterBinding
import com.example.tokopaerbe.helper.SnK
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import com.example.tokopaerbe.viewmodel.ViewModelFactory

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<PreLoginViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

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
        initObserver()
    }


    private fun initView() {
        binding.toolbar.title = getString(R.string.register)
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
                val request = RegisterRequest(
                    email = binding.emailEditText.text.toString().trim(),
                    firebaseToken = "",
                    password = binding.passowrdEditText.text.toString().trim()
                )
                viewModel.fetchRegister(request)
            }
            /**
             * Button for Password Check
             */
            it.passowrdEditText.addTextChangedListener(TextWatcherConfigure(1) {
                    password -> isValidPassword(password)
            })

        }
    }


    private fun initObserver() = with(viewModel) {
        response.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
        }
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


    fun tColor() {
        val sk = binding.syaratKetentuan
        val fullText = getString(R.string.term_condition_login)
        val defaultLocale = resources.configuration.locales[0].language
        sk.text = context?.let { SnK.applyCustomTextColor(defaultLocale, it, fullText) }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}
