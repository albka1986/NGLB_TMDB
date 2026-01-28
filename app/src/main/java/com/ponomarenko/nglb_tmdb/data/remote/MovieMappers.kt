package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import com.ponomarenko.nglb_tmdb.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "$TMDB_IMAGE_BASE_URL/w500$it" },
        releaseDate = releaseDate,
        rating = popularity
    )
}
