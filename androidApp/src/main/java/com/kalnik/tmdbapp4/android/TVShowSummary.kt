package com.kalnik.tmdbapp4.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.kalnik.tmdbapp4.androi.ui.theme.Purple700
import com.kalnik.tmdbapp4.presentation.TVShow
import com.kalnik.tmdbapp4.presentation.TVShowsState

@Composable
fun TVShowSummary(
    name: String,
    overview: String,
    originCountries: List<String>,
    backdropImageUrl: String?,
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (backdropImageUrl != null) {
            Image(
                painter = rememberImagePainter(backdropImageUrl) {
                    // Workaround for https://issuetracker.google.com/issues/186012457
                    size(OriginalSize)
                },
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(8.dp))
        }
        Text(
            text = overview,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = originCountries.joinToString(),
            style = MaterialTheme.typography.caption
        )
    }
}

private val exampleTVShow = TVShow(
    id = 1234,
    name = "Some series with a quite long name",
    overview = "Series summary where the action is described. This is a really cool" +
            "series about a group of friends which travel together.",
    originCountries = listOf("KR", "US"),
    backdropImageUrl = "https://image.tmdb.org/t/p/w780/sjx6zjQI2dLGtEL0HGWsnq6UyLU.jpg",
)

@Preview
@Composable
fun ExampleTvShow() {
    TVShowSummary(
        name = exampleTVShow.name,
        overview = exampleTVShow.overview,
        originCountries = exampleTVShow.originCountries,
        backdropImageUrl = exampleTVShow.backdropImageUrl,
    )
}

@Composable
fun TVShowSummaryList(
    tvShows: List<TVShow>
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tvShows) {
            TVShowSummary(
                name = it.name,
                overview = it.overview,
                originCountries = it.originCountries,
                backdropImageUrl = it.backdropImageUrl,
            )
        }
    }
}

@Composable
fun TVShowSummariesScreen(uiState: TVShowsState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Popular TV Shows") },
                backgroundColor = Purple700
            )
        }
    ) {
        when (uiState) {
            TVShowsState.Loading ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Loading")
                }
            is TVShowsState.TVShows -> TVShowSummaryList(
                tvShows = uiState.tvShows
            )
        }
    }
}

@Preview
@Composable
fun ExampleTvShowSummariesScreen() {
    val uiState = TVShowsState.TVShows(tvShows = listOf(exampleTVShow))
    TVShowSummariesScreen(uiState = uiState)
}
