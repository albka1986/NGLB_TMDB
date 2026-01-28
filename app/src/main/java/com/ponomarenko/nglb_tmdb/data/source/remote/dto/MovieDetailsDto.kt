package com.ponomarenko.nglb_tmdb.data.source.remote.dto

import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class MovieDetailsDto(
    val id: Int,
    val title: String,
    val overview: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    val genres: List<GenreDto> = emptyList()
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/original$it" },
        releaseDate = releaseDate?.let { dateFormat.parse(it) },
        voteAverage = voteAverage,
        genres = genres.map { it.name }
    )
}
