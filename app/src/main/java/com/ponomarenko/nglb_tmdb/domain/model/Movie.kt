package com.ponomarenko.nglb_tmdb.domain.model

import java.util.Date

data class Movie(
    val id: Int,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: Date? = null
)