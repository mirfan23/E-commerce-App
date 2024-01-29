package com.example.tokopaerbe.di

import com.example.tokopaerbe.viewmodel.PreLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PreLoginViewModel(get()) }
}