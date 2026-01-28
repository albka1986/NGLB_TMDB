package com.ponomarenko.nglb_tmdb.util

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val ORIGIN_FORMAT_DATE = "yyyy-MM-dd"
private const val GERMAN_FORMAT_DATE = "dd.MM.yyyy"

fun String.toDate(): Date? {
    val parser = SimpleDateFormat(ORIGIN_FORMAT_DATE, Locale.GERMANY)
    return try {
        parser.parse(this)
    } catch (e: ParseException) {
        Timber.e(e)
        null
    }
}

fun Date.toGermanFormattedString(): String =
    SimpleDateFormat(GERMAN_FORMAT_DATE, Locale.GERMANY).format(this)