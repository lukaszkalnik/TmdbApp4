package com.kalnik.tmdbapp4.di

import com.kalnik.tmdbapp4.presentation.TVShowsSharedViewModel
import org.koin.core.Koin

@Suppress("unused")
fun Koin.getTVShowsSharedViewModel(): TVShowsSharedViewModel = get()
