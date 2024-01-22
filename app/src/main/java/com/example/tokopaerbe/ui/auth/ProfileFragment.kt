package com.example.tokopaerbe.ui.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentProfileBinding
import com.example.tokopaerbe.helper.SnK

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFinish.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }
        tColor()
    }
    fun tColor() {
        val sk = binding.syaratKetentuan
        sk.text = context?.let {
            SnK.applyCustomTextColor(
                it,
                "Dengan masuk disini, kamu menyetujui Syarat & Ketentuan \n serta Kebijakan Privasi TokoPhincon"
            )
        }
        sk.movementMethod = LinkMovementMethod.getInstance()
    }
}
