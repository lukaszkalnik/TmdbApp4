package com.kalnik.tmdbapp4.android

import android.app.Application
import com.kalnik.tmdbapp4.di.CommonModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(CommonModule.module)
        }
    }
}