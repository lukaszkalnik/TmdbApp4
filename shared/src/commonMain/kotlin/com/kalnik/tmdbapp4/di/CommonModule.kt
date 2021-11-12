package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.data.TmdbApi
import io.ktor.client.HttpClient
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(): KoinApplication = startKoin {
    modules(commonModule)
}

@Suppress("RemoveExplicitTypeArguments")
val commonModule = module {
    single<HttpClient> { TmdbApi.createHttpClient() }
    single<TmdbApi> { TmdbApi(client = get()) }
}
