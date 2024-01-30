package com.example.core.di

import android.content.Context
import android.content.SharedPreferences
import com.catnip.core.base.BaseModules
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.repository.AuthRepositoryImpl
import com.example.core.domain.usecase.AuthInteractor
import com.example.core.domain.usecase.AuthUseCase
import com.example.core.local.LocalDataSource
import com.example.core.local.preferences.SharedPreferenceImpl
import com.example.core.local.preferences.SharedPreferenceImpl.Companion.PREFS_NAME
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.client.NetworkClient
import com.example.core.remote.datasource.RemoteDataSource
import com.example.core.remote.interceptor.AuthInterceptor
import com.example.core.remote.interceptor.SessionInterceptor
import com.example.core.remote.interceptor.TokenInterceptor
import com.example.core.remote.service.ApiEndPoint
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object CoreModule : BaseModules {

    val sharedPrefModule = module {
        single { androidContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }
        single<SharedPreferencesHelper> {
            SharedPreferenceImpl(get())
        }
    }

    val networkModule = module {
        single { AuthInterceptor(get()) }
        single { SessionInterceptor(get()) }
        single { TokenInterceptor(get(), get()) }
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { NetworkClient(get(), get(), get(), get()) }
        single<ApiEndPoint> { get<NetworkClient>().create() }
    }

    val dataSourceModule = module {
        single { RemoteDataSource(get()) }
        single { LocalDataSource() }
    }

    val repositoryModule = module {
        single<AuthRepository> { AuthRepositoryImpl(get()) }
    }

    val useCaseModule = module {
        single<AuthUseCase> { AuthInteractor(get(), get()) }
    }

    override fun getModules(): List<Module> =
        listOf(sharedPrefModule, networkModule, dataSourceModule, repositoryModule, useCaseModule)


}