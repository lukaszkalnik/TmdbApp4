package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.data.TmdbApi
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin {
    modules(CommonModule.module)
}

class CommonModule {

    companion object {

        @Suppress("RemoveExplicitTypeArguments")
        val module = module {
            single<HttpClient> { TmdbApi.createHttpClient() }
            single<TmdbApi> { TmdbApi(client = get()) }
        }
    }
}
