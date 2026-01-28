package com.ponomarenko.nglb_tmdb.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val genres: List<String> = emptyList()
)