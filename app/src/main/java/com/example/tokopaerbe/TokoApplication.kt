package com.example.tokopaerbe

import android.app.Application
import com.example.core.di.coreModule
import com.example.core.di.networkModule
import com.example.tokopaerbe.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TokoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(this@TokoApplication)
            modules(listOf(viewModelModule, coreModule, networkModule))
        }
    }
}