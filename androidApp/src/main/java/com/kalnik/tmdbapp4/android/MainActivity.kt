package com.kalnik.tmdbapp4.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.kalnik.tmdbapp4.data.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val tmdbApi: TmdbApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            val series = withContext(Dispatchers.IO) {
                tmdbApi.getPopularTVShows()
            }

            setContent {
                InfoText(series.toString())
            }
        }
    }
}

@Composable
fun InfoText(text: String) {
    Text(text = text)
}
