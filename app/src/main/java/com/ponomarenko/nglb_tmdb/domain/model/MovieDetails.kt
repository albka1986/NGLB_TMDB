package com.ponomarenko.nglb_tmdb.domain.model

import java.util.Date

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: Date?,
    val voteAverage: Double?,
    val genres: List<String> = emptyList()
)