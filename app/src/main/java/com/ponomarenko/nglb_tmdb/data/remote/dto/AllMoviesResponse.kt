package com.ponomarenko.nglb_tmdb.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AllMoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
)