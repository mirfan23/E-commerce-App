package com.example.tokopaerbe.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {
    private lateinit var binding: FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionBinding.inflate(inflater, container, false)

        return binding.root
    }
}