package com.example.tokopaerbe.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.TextWatcherConfigure
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
        binding.passwordTextInputLayout.isErrorEnabled = true
        binding.passwordTextInputLayout.error = errorMessage
        println("Error: $errorMessage")
    }

    private fun clearError() {
        binding.passwordTextInputLayout.isErrorEnabled = false
        println("Password valid")
    }


    fun tColor() {
        val sk = binding.syaratKetentuan
        sk.text = SnK.applyCustomTextColo(
            requireContext(),
            "Dengan masuk disini, kamu menyetujui Syarat & Ketentuan \n serta Kebijakan Privasi TokoPhincon"
        )
    }
}
