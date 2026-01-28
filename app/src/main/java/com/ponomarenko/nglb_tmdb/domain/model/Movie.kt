package com.ponomarenko.nglb_tmdb.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String? = null,
    val releaseDate: String,
    val rating: Float?
)