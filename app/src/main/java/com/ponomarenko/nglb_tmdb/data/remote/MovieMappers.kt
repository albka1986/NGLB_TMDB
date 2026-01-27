package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.domain.model.Movie

fun MovieDto.toDomain(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = poster_path,
        releaseDate = release_date
    )

