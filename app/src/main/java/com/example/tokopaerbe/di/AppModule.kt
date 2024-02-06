package com.example.tokopaerbe.di

import com.catnip.core.base.BaseModules
import com.example.tokopaerbe.viewmodel.DashBoardViewModel
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule : BaseModules {
    val viewModelModule = module {
        viewModel { PreLoginViewModel(get()) }
        viewModel { DashBoardViewModel(get()) }
    }

    override fun getModules(): List<Module> = listOf(viewModelModule)
}