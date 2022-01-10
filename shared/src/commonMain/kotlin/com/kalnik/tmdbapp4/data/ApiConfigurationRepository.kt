package com.kalnik.tmdbapp4.data

internal interface ApiConfigurationRepository {
    var imageBaseUrl: String?
    val backdropSize: String?

    fun updateBackdropSizes(availableSizes: List<String>)
}

private const val DESIRED_BACKDROP_SIZE = "w780"

internal object ApiConfigurationRepositoryImpl : ApiConfigurationRepository {
    override var imageBaseUrl: String? = null
    override var backdropSize: String? = null

    override fun updateBackdropSizes(availableSizes: List<String>) {
        if (availableSizes.contains(DESIRED_BACKDROP_SIZE))
            backdropSize = DESIRED_BACKDROP_SIZE
        else
            backdropSize = findNearestSmallerSize(DESIRED_BACKDROP_SIZE, availableSizes)
    }

    private fun findNearestSmallerSize(desiredSize: String, availableSizes: List<String>): String? =
        availableSizes
            .filter { it.startsWith("w") }
            .findLast {
                it.removePrefix("w").toIntOrNull() ?: 0 < DESIRED_BACKDROP_SIZE.removePrefix("w").toInt()
            }
}
