package com.kalnik.tmdbapp4.presentation

import com.kalnik.tmdbapp4.data.TmdbApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface PopularTVShowsViewModel {

    val uiState: StateFlow<PopularTVShowsState>
}

internal class PopularTVShowsViewModelImpl(
    dispatcher: CoroutineDispatcher = Dispatchers.Main
) : PopularTVShowsViewModel,
    KoinComponent {

    private val coroutineScope = CoroutineScope(dispatcher)

    private val _uiState: MutableStateFlow<PopularTVShowsState> = MutableStateFlow(PopularTVShowsState.TVShows())
    override val uiState: StateFlow<PopularTVShowsState> = _uiState.asStateFlow()

    private val tmdbApi: TmdbApi by inject()

    init {
        _uiState.value = PopularTVShowsState.Loading
        coroutineScope.launch {
            val tvShowsPage = tmdbApi.getPopularTVShows()
            val tvShows = tvShowsPage.results.map {
                TVShow(
                    id = it.id,
                    name = it.name,
                    overview = it.overview,
                    originCountries = it.originCountries
                )
            }
            _uiState.value = PopularTVShowsState.TVShows(tvShows)
        }
    }
}

data class TVShow(
    val id: Int,
    val name: String,
    val overview: String,
    val originCountries: List<String>,
)

sealed class PopularTVShowsState {

    object Loading : PopularTVShowsState()

    data class TVShows(val tvShows: List<TVShow> = emptyList()) : PopularTVShowsState()
}
