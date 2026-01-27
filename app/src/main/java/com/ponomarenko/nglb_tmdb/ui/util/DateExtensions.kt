package com.ponomarenko.nglb_tmdb.ui.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toGermanFormattedString(): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY)
    return format.format(this)
}