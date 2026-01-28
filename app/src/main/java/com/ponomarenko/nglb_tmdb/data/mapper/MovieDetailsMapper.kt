package com.ponomarenko.nglb_tmdb.data.mapper

import com.ponomarenko.nglb_tmdb.data.remote.TmdbConstants
import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDetailsDto
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import com.ponomarenko.nglb_tmdb.util.toGermanFormattedString
import com.ponomarenko.nglb_tmdb.util.toLocalDate

fun MovieDetailsDto.toMovieDetails(): MovieDetails =
    MovieDetails(
        id = id,
        title = title,
        overview = overview.orEmpty(),
        posterPath = posterPath?.let { "${TmdbConstants.IMAGE_BASE_URL}${TmdbConstants.POSTER_SIZE_W500}$it" },
        releaseDate = releaseDate?.toLocalDate()
            ?.toGermanFormattedString()
            .orEmpty(),
        genres = genres.map { it.name }
    )