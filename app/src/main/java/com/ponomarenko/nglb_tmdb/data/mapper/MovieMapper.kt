package com.ponomarenko.nglb_tmdb.data.mapper

import com.ponomarenko.nglb_tmdb.data.remote.TmdbConstants
import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import com.ponomarenko.nglb_tmdb.domain.model.Movie

fun MovieDto.toMovie(): Movie =
    Movie(
        id = id,
        title = title,
        posterPath = posterPath?.let { "${TmdbConstants.POSTER_URL_W500}$it" },
        releaseDate = releaseDate.orEmpty(),
        rating = popularity
    )