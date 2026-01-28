package com.ponomarenko.nglb_tmdb.domain.use_case

import androidx.paging.PagingData
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}
