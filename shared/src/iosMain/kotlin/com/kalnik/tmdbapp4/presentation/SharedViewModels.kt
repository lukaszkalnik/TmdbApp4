package com.kalnik.tmdbapp4.presentation

import com.kalnik.tmdbapp4.coroutines.CFlow
import com.kalnik.tmdbapp4.coroutines.wrap


@Suppress("unused")
fun watchUiState(viewModel: TVShowsSharedViewModel): CFlow<TVShowsState> = viewModel.uiState.wrap()
