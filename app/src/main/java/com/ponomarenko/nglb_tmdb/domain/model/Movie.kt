package com.ponomarenko.nglb_tmdb.domain.model

import androidx.compose.runtime.Immutable
import java.util.Date

@Immutable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: Date? = null
)