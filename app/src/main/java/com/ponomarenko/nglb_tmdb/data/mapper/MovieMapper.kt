package com.ponomarenko.nglb_tmdb.data.mapper

import com.ponomarenko.nglb_tmdb.data.remote.TmdbConstants
import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import com.ponomarenko.nglb_tmdb.domain.model.Movie

fun MovieDto.toMovie(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "${TmdbConstants.IMAGE_BASE_URL}${TmdbConstants.POSTER_SIZE_W500}$it" },
        releaseDate = releaseDate,
        rating = popularity
    )