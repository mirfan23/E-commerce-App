package com.example.tokopaerbe.di

import com.catnip.core.base.BaseFeatureModule
import com.example.core.domain.repository.ProductRepository
import com.example.core.domain.repository.ProductRepositoryImpl
import com.example.core.domain.usecase.AppInteractor
import com.example.core.domain.usecase.AppUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object ProductModule: BaseFeatureModule {
    override fun repository(): Module = module {
        single<ProductRepository> { ProductRepositoryImpl(get(), get(), get()) }
    }

    override fun useCase(): Module = module {
        single<AppUseCase> { AppInteractor(get(), get(), get(), get()) }
    }
    override fun viewModel(): Module = module {}

    override fun getModules(): List<Module> = listOf(repository(), useCase(), viewModel())
}