package com.example.tokopaerbe.ui.dashboard

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentHomeBinding
import com.example.tokopaerbe.helper.Helper
import com.example.tokopaerbe.helper.Helper.Companion.getSharedPreferences
import com.example.tokopaerbe.helper.checkIf

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogout.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }

        val themeChecker = Helper.getThemeStatus(requireContext(), "dark")
        binding.switchTheme.checkIf(themeChecker)

        binding.switchTheme.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    Helper.putThemeStatus(requireContext(), "dark", true)
                }
                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    Helper.putThemeStatus(requireContext(), "dark", false)
                }
            }
        }
    }
}