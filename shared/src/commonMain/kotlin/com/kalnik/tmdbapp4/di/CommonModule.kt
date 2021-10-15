package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.data.TmdbApi
import io.ktor.client.*
import org.koin.dsl.module

class CommonModule {

    companion object {

        @Suppress("RemoveExplicitTypeArguments")
        val module = module {
            single<HttpClient> { TmdbApi.createHttpClient() }
            single<TmdbApi> { TmdbApi(client = get()) }
        }
    }
}
