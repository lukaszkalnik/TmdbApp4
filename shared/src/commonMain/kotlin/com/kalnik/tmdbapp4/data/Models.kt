package com.kalnik.tmdbapp4.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TVShowsPage(
    val page: Int,
    val results: List<TVShow>,
    @SerialName("total_pages") val totalPages: Int
)

@Serializable
data class TVShow(
    val name: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("origin_country") val originCountries: List<String>
)
