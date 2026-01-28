package com.ponomarenko.nglb_tmdb.domain.use_case

import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import com.ponomarenko.nglb_tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<Result<MovieDetails>> {
        return repository.getMovieDetails(movieId)
    }
}
