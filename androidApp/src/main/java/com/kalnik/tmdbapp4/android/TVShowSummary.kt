package com.kalnik.tmdbapp4.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kalnik.tmdbapp4.data.TVShow

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

@Preview
@Composable
fun ExampleTvShow() {
    TVShowSummary(
        name = "Some series with a quite long name",
        overview = "Series summary where the action is described. This is a really cool" +
                "series about a group of friends which travel together.",
        originCountries = listOf("KR", "US")
    )
}

@Composable
fun TVShowSummaryList(
    modifier: Modifier,
    tvShows: List<TVShow>
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tvShows) {
            TVShowSummary(
                name = it.name,
                overview = it.overview,
                originCountries = it.originCountries
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TVShowSummariesScreen(tvShows: List<TVShow>) {
    TVShowSummaryList(
        modifier = Modifier.padding(16.dp),
        tvShows = tvShows
    )
}
