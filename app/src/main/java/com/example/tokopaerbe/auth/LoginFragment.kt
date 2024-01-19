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
import com.example.tokopaerbe.dashboard.DashboardFragment
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
                navigateToFragment(DashboardFragment())
            }
            it.buttonRegister.setOnClickListener {
                navigateToFragment(RegisterFragment())
            }
            it.emailEditText.addTextChangedListener(TextWatcherConfigure(1) {
                    email -> validateEmail(email)
            })
            it.passwordEditText.addTextChangedListener(TextWatcherConfigure(1) {
                    password -> isValidPassword(password)
            })
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
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
    fun tColor() {
        val sk = binding.syaratKetentuan
        sk.text = SnK.applyCustomTextColo(
            requireContext(),
            "Dengan masuk disini, kamu menyetujui Syarat & Ketentuan \n serta Kebijakan Privasi TokoPhincon"
        )
    }

}