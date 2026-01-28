package com.ponomarenko.nglb_tmdb.util

import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private val originDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

private val germanDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

fun String.toLocalDate(): LocalDate? {
    return try {
        LocalDate.parse(this, originDateFormatter)
    } catch (e: DateTimeParseException) {
        Timber.e(e, "Failed to parse date string: $this")
        null
    }
}

fun LocalDate.toGermanFormattedString(): String {
    return this.format(germanDateFormatter)
}
