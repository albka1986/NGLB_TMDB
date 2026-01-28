package com.ponomarenko.nglb_tmdb.domain.use_case

import androidx.paging.PagingData
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val repository: MovieRepository) {

    operator fun invoke(query: String): Flow<PagingData<Movie>> =
        repository.searchMovies(query)
}