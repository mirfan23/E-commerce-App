package com.example.tokopaerbe.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =  childFragmentManager.findFragmentById(R.id.fragment_container_dashboard) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavbar.setupWithNavController(navController)
    }
}