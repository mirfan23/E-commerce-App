package com.example.tokopaerbe.view.dashboard

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.window.layout.WindowMetricsCalculator
import com.catnip.core.base.BaseFragment
import com.example.core.utils.launchAndCollectIn
import com.example.tokopaerbe.R
import com.example.tokopaerbe.databinding.FragmentDashboardBinding
import com.example.tokopaerbe.viewmodel.DashBoardViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashBoardViewModel>(FragmentDashboardBinding::inflate) {
    override val viewModel: DashBoardViewModel by viewModel()
    private lateinit var navController: NavController

    override fun initView() {
        viewModel.putProfileName()

        val navHostFragment =  childFragmentManager.findFragmentById(R.id.fragment_container_dashboard) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun initListener() = with(binding) {
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

        toolbar.setOnMenuItemClickListener{menuItem ->
            when(menuItem.itemId) {
                R.id.cart_menu -> {
                    findNavController().navigate(R.id.action_dashboardFragment_to_cartFragment)

                    true
                }
                R.id.notif_menu -> {
                    /**
                     * nanti dipake lagi
                     */
                    findNavController().navigate(R.id.action_dashboardFragment_to_paymentFragment)

                    true
                }
                else -> false
            }

        }
    }

    override fun observeData() {
        with(viewModel) {
            name.launchAndCollectIn(viewLifecycleOwner) {
                binding.toolbar.title = it
            }
        }

    }
}