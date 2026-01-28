package com.ponomarenko.nglb_tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ponomarenko.nglb_tmdb.data.mapper.toMovie
import com.ponomarenko.nglb_tmdb.data.mapper.toMovieDetails
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi
import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import com.ponomarenko.nglb_tmdb.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber

private const val NETWORK_PAGE_SIZE = 20

class MovieRepositoryImpl(private val api: TmdbApi) : MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                AllMoviesPagingSource(api = api)
            },
        ).flow
            .map { pagingData -> pagingData.map(MovieDto::toMovie) }


    override fun getMovieDetails(movieId: Int): Flow<Result<MovieDetails>> =
        flow {
            try {
                val movieDetailsDto = api.fetchMovieDetails(movieId)
                emit(Result.success(movieDetailsDto.toMovieDetails()))
            } catch (e: Exception) {
                Timber.e(e, "Failed to fetch movie details for id: $movieId")
                emit(Result.failure(e))
            }
        }.flowOn(Dispatchers.IO)
}
