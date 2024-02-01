package com.example.tokopaerbe.view.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.window.layout.WindowMetricsCalculator
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =  childFragmentManager.findFragmentById(R.id.fragment_container_dashboard) as NavHostFragment
        navController = navHostFragment.navController

        initView()
        initListener()
    }

    private fun initView() {
        binding.toolbar.title = getString(R.string.username)
    }

    private fun initListener() = with(binding) {
        val metrics = activity?.let { activity ->
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(
                activity
            )
        }

        val withDP = metrics?.bounds?.width()?.div(resources.displayMetrics.density)

        if(withDP != null) {
            when {
                withDP < 600f -> {
                    val bottomNav = binding.bottomNavbar as BottomNavigationView
                    bottomNav.setupWithNavController(navController)
                }

                withDP < 840f -> {
                    val bottomNav = binding.bottomNavbar as NavigationRailView
                    bottomNav.setupWithNavController(navController)
                }

                else -> {
                    val bottomNav = binding.bottomNavbar as NavigationView
                    bottomNav.setupWithNavController(navController)
                }
            }
        }
    }

    fun logOutHandler() {
        findNavController().navigate(R.id.action_dashboardFragment_to_loginFragment)
    }
}