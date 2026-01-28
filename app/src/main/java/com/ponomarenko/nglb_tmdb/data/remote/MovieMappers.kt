package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val parser: SimpleDateFormat = SimpleDateFormat(ORIGIN_FORMAT_DATE, Locale.GERMANY)

private fun MovieDto.date(parser: SimpleDateFormat): Date? =
    releaseDate?.let {
        try {
            parser.parse(it)
        } catch (e: ParseException) {
            Timber.e(e)
            null
        }
    }

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "$TMDB_IMAGE_DOMAIN$it" },
        releaseDate = date(parser)
    )
}

private const val ORIGIN_FORMAT_DATE = "yyyy-MM-dd"
private const val TMDB_IMAGE_DOMAIN = "https://image.tmdb.org/t/p/w500/"