package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import com.ponomarenko.nglb_tmdb.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "$TMDB_IMAGE_DOMAIN$it" },
        releaseDate = releaseDate,
        rating = popularity
    )
}

private const val TMDB_IMAGE_DOMAIN = "https://image.tmdb.org/t/p/w500/"
