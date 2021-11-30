package com.kalnik.tmdbapp4.data

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

internal class TmdbApi(
    private val client: HttpClient
) {

    suspend fun getPopularTVShows(): TVShowsPage = client.get("/tv/popular")

    companion object {

        internal fun createHttpClient() = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    encodedPath = "/3/$encodedPath" // prepend API version to path
                }
                parameter("api_key", "29e8a956d0a063f73d8309d81774dda1")
            }
        }
    }
}
