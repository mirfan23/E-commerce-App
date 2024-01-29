package com.example.core.di

import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.repository.AuthRepositoryImpl
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.domain.usecase.AuthInteractor
import com.example.core.local.LocalDataSource
import com.example.core.remote.datasource.RemoteDataSource
import org.koin.dsl.module

val coreModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single {
        RemoteDataSource(get())
    }
    single {
        LocalDataSource(get())
    }

    single<AuthUseCase> {
        AuthInteractor(get())
    }
}