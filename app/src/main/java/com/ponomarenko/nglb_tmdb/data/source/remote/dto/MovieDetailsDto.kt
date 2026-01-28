package com.ponomarenko.nglb_tmdb.data.source.remote.dto

import com.ponomarenko.nglb_tmdb.data.remote.TMDB_IMAGE_BASE_URL
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { "$TMDB_IMAGE_BASE_URL/w500$it" },
        backdropPath = backdropPath?.let { "$TMDB_IMAGE_BASE_URL/original$it" },
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genres = genres.map { it.name }
    )
}
