package com.kalnik.tmdbapp4.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.http.parametersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class TmdbApi(
    private val client: HttpClient
) {

    suspend fun getConfiguration(): ApiConfig = client.get("configuration").body()

    suspend fun getPopularTVShows(): TVShowsPage = client.get("tv/popular").body()

    companion object {

        internal fun createHttpClient() = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    encodedPath = "/3$encodedPath" // prepend API version to path
                    parametersOf("api_key", "29e8a956d0a063f73d8309d81774dda1")
                }
            }
        }
    }
}
