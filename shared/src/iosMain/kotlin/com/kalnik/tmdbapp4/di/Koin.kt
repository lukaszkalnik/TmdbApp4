package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.presentation.PopularTVShowsViewModel
import org.koin.core.Koin

@Suppress("unused")
fun Koin.getPopularTVShowsViewModel(): PopularTVShowsViewModel = get()
