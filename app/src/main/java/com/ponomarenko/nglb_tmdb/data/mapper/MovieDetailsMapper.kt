package com.ponomarenko.nglb_tmdb.data.mapper

import com.ponomarenko.nglb_tmdb.data.source.remote.dto.MovieDetailsDto
import com.ponomarenko.nglb_tmdb.di.TMDB_IMAGE_BASE_URL
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails

fun MovieDetailsDto.toMovieDetails(): MovieDetails =
    MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "$TMDB_IMAGE_BASE_URL/w500$it" },
        backdropPath = backdropPath?.let { "$TMDB_IMAGE_BASE_URL/original$it" },
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genres = genres.map { it.name }
    )