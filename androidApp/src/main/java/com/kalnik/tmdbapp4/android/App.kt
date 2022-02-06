package com.kalnik.tmdbapp4.android

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.kalnik.tmdbapp4.di.initKoin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class App : Application(),
    ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    override fun newImageLoader(): ImageLoader {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return ImageLoader.Builder(applicationContext)
            .crossfade(true)
            .okHttpClient {
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            }
            .build()
    }
}