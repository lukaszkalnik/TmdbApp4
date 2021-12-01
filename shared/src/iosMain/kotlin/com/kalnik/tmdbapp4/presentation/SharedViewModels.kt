package com.kalnik.tmdbapp4.presentation

import com.kalnik.tmdbapp4.coroutines.CFlow


fun watchUiState(viewModel: TVShowsSharedViewModel): CFlow<TVShowsState> = CFlow(viewModel.uiState)
