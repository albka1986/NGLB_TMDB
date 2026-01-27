package com.ponomarenko.nglb_tmdb.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null
)