package com.ponomarenko.nglb_tmdb.domain.repository

import androidx.paging.PagingData
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    
    fun getMovies(): Flow<PagingData<Movie>>
    fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>>
}
