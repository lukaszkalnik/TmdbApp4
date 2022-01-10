package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.data.ApiConfigurationRepository
import com.kalnik.tmdbapp4.data.ApiConfigurationRepositoryImpl
import com.kalnik.tmdbapp4.data.TmdbApi
import com.kalnik.tmdbapp4.presentation.TVShowsSharedViewModel
import com.kalnik.tmdbapp4.presentation.TVShowsSharedViewModelImpl
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
    single<TVShowsSharedViewModel> { TVShowsSharedViewModelImpl() }
    single<ApiConfigurationRepository> { ApiConfigurationRepositoryImpl }
}
