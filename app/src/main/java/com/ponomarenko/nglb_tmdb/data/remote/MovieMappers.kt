package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.domain.model.Movie
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

fun MovieDto.toDomain(): Movie {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)
    val parsedDate = release_date?.let {
        try {
            parser.parse(it)
        } catch (e: java.text.ParseException) {
            Timber.e(e)
            null
        }
    }

    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = poster_path,
        releaseDate = parsedDate
    )
}