@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)

package com.ponomarenko.nglb_tmdb.data.remote

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
    ): PopularMoviesResponse
}

@Serializable
data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieDto>,
)

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String? = null,
    val poster_path: String? = null,
    val release_date: String? = null
)

