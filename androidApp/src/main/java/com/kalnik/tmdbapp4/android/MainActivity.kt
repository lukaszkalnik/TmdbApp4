package com.kalnik.tmdbapp4.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import com.kalnik.tmdbapp4.presentation.TVShowsSharedViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val tvShowsSharedViewModel: TVShowsSharedViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            setContent {
                val tvShowsState by tvShowsSharedViewModel.uiState.collectAsState()
                TVShowSummariesScreen(uiState = tvShowsState)
            }
        }
    }
}
