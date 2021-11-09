package com.kalnik.tmdbapp4.android

import android.app.Application
import com.kalnik.tmdbapp4.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}