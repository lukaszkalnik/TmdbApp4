package com.kalnik.tmdbapp4.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kalnik.tmdbapp4.androi.ui.theme.Purple700
import com.kalnik.tmdbapp4.presentation.TVShow
import com.kalnik.tmdbapp4.presentation.TVShowsState

@Composable
fun TVShowSummary(
    name: String,
    overview: String,
    originCountries: List<String>,
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
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
    originCountries = listOf("KR", "US")
)

@Preview
@Composable
fun ExampleTvShow() {
    TVShowSummary(
        name = exampleTVShow.name,
        overview = exampleTVShow.overview,
        originCountries = exampleTVShow.originCountries
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
                originCountries = it.originCountries
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
