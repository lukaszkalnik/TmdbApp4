package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.data.TmdbApi
import org.koin.core.Koin

@Suppress("unused")
fun Koin.getTmdbApi(): TmdbApi = get()
