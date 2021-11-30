package com.kalnik.tmdbapp4.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.kalnik.tmdbapp4.presentation.PopularTVShowsViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val popularTVShowsViewModel: PopularTVShowsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            setContent {
                val popularTVShowsState by popularTVShowsViewModel.uiState.collectAsState()
                TVShowSummariesScreen(uiState = popularTVShowsState)
            }
        }
    }
}
