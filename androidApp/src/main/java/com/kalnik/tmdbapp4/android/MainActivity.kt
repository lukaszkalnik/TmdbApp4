package com.kalnik.tmdbapp4.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.kalnik.tmdbapp4.data.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : ComponentActivity() {

    val tmdbApi: TmdbApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val tvShowsPage = withContext(Dispatchers.IO) {
                tmdbApi.getPopularTVShows()
            }

            setContent {
                TVShowSummaryList(tvShows = tvShowsPage.results)
            }
        }
    }
}
