package com.ponomarenko.nglb_tmdb.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val genres: List<String> = emptyList()
)