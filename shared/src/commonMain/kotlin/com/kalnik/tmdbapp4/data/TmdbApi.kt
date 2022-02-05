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
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val API_KEY = "api_key"
private const val API_KEY_VALUE = "29e8a956d0a063f73d8309d81774dda1"

internal class TmdbApi(
    private val client: HttpClient
) {

    suspend fun getConfiguration(): ApiConfig = client.get("configuration") {
        parameter(API_KEY, API_KEY_VALUE)
    }.body()

    suspend fun getPopularTVShows(): TVShowsPage = client.get("tv/popular") {
        parameter(API_KEY, API_KEY_VALUE)
    }.body()

    companion object {

        internal fun createHttpClient() = HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
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
                }
            }
        }
    }
}
