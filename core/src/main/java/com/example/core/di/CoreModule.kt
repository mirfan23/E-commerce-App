package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.catnip.core.base.BaseModules
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.core.domain.repository.AuthRepository
import com.example.core.domain.repository.AuthRepositoryImpl
import com.example.core.domain.repository.ProductRepository
import com.example.core.domain.repository.ProductRepositoryImpl
import com.example.core.domain.usecase.AppInteractor
import com.example.core.domain.usecase.AppUseCase
import com.example.core.local.LocalDataSource
import com.example.core.local.PagingDataSource
import com.example.core.local.database.Database
import com.example.core.local.preferences.SharedPreferenceImpl
import com.example.core.local.preferences.SharedPreferenceImpl.Companion.PREFS_NAME
import com.example.core.local.preferences.SharedPreferencesHelper
import com.example.core.remote.client.NetworkClient
import com.example.core.remote.RemoteDataSource
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
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { AuthInterceptor(get()) }
        single { SessionInterceptor(get()) }
        single { TokenInterceptor(get(), get()) }
        single { NetworkClient(get(), get(), get(), get()) }
        single<ApiEndPoint> { get<NetworkClient>().create() }
    }

    val dataSourceModule = module {
        single { RemoteDataSource(get()) }
        single { LocalDataSource(get(), get()) }
        single { PagingDataSource(get(), get()) }
    }

    val database = module {
        single {
            Room.databaseBuilder(androidContext(), Database::class.java, "app_database")
                .addMigrations(Database.MIGRATION_1_2)
                .addMigrations(Database.MIGRATION_2_3)
                .addMigrations(Database.MIGRATION_3_4)
                .addMigrations(Database.MIGRATION_4_5)
                .addMigrations(Database.MIGRATION_5_6)
                .fallbackToDestructiveMigration()
                .build()
        }
        single { get<Database>().appDao() }
    }

    val authRepositoryModule = module {
        single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    }

    val productRepositoryModule = module {
        single<ProductRepository> { ProductRepositoryImpl(get(), get(), get()) }
    }

    val useCaseModule = module {
        single<AppUseCase> { AppInteractor(get(), get(), get()) }
    }

    override fun getModules(): List<Module> =
        listOf(
            sharedPrefModule,
            networkModule,
            dataSourceModule,
            authRepositoryModule,
            useCaseModule,
            productRepositoryModule,
            database
        )


}