package com.ponomarenko.nglb_tmdb.data.remote

import com.ponomarenko.nglb_tmdb.data.remote.dto.PopularMoviesResponse
import com.ponomarenko.nglb_tmdb.data.source.remote.dto.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("discover/movie")
    suspend fun fetchAllMovies(
        @Query("page") page: Int = 1,
        @Query(value = "sort_by") sorBy: String = "primary_release_date.desc"
    ): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsDto
}
