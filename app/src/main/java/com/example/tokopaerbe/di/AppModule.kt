package com.example.tokopaerbe.di

import com.catnip.core.base.BaseModules
import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import com.example.tokopaerbe.viewmodel.SharedPreferencesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule : BaseModules{
val viewModelModule = module {
    viewModel { PreLoginViewModel(get()) }
    viewModel { SharedPreferencesViewModel(get()) }
}
    override fun getModules(): List<Module> = listOf(viewModelModule)
}