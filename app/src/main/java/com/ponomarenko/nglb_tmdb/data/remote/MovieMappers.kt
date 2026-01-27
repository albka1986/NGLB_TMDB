package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.domain.model.Movie
import java.text.SimpleDateFormat
import java.util.Locale

fun MovieDto.toDomain(): Movie {
    // Assuming the date from the backend is in "yyyy-MM-dd" format.
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    val parsedDate = release_date?.let {
        try {
            parser.parse(it)
        } catch (e: java.text.ParseException) {
            // Handle parsing error, maybe log it. For now, return null.
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
