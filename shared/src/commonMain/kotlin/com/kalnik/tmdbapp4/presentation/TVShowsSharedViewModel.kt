package com.kalnik.tmdbapp4.presentation

import com.kalnik.tmdbapp4.data.ApiConfigurationRepository
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

interface TVShowsSharedViewModel {

    val uiState: StateFlow<TVShowsState>
}

internal class TVShowsSharedViewModelImpl(
    dispatcher: CoroutineDispatcher = Dispatchers.Main
) : TVShowsSharedViewModel,
    KoinComponent {

    private val coroutineScope = CoroutineScope(dispatcher)

    private val _uiState: MutableStateFlow<TVShowsState> = MutableStateFlow(TVShowsState.TVShows())
    override val uiState: StateFlow<TVShowsState> = _uiState.asStateFlow()

    private val tmdbApi: TmdbApi by inject()
    private val apiConfigRepository: ApiConfigurationRepository by inject()

    init {
        _uiState.value = TVShowsState.Loading
        coroutineScope.launch {
            val apiConfig = tmdbApi.getConfiguration()
            with(apiConfigRepository) {
                imageBaseUrl = apiConfig.images.baseUrl
                updateBackdropSizes(apiConfig.images.backdropSizes)
            }
            val backdropBaseUrl = with(apiConfigRepository) { imageBaseUrl + backdropSize }

            val tvShows = tmdbApi.getPopularTVShows().results.map { tvShowResult ->
                TVShow(
                    id = tvShowResult.id,
                    name = tvShowResult.name,
                    overview = tvShowResult.overview,
                    originCountries = tvShowResult.originCountries,
                    backdropImageUrl = tvShowResult.backdropPath?.let { backdropBaseUrl + it },
                )
            }
            _uiState.value = TVShowsState.TVShows(tvShows)
        }
    }
}

data class TVShow(
    val id: Int,
    val name: String,
    val overview: String,
    val originCountries: List<String>,
    val backdropImageUrl: String?,
)

sealed class TVShowsState {

    object Loading : TVShowsState()

    data class TVShows(val tvShows: List<TVShow> = emptyList()) : TVShowsState()
}
