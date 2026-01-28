package com.ponomarenko.nglb_tmdb.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
)
