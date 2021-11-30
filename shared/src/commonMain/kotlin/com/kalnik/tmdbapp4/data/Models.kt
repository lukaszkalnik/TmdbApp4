package com.kalnik.tmdbapp4.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TVShowsPage(
    val page: Int,
    val results: List<TVShow>,
    @SerialName("total_pages") val totalPages: Int
)

@Serializable
internal data class TVShow(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("origin_country") val originCountries: List<String>,
)
