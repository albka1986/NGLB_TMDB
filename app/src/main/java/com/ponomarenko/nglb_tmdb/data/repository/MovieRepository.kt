package com.ponomarenko.nglb_tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ponomarenko.nglb_tmdb.data.remote.MovieDto
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi
import com.ponomarenko.nglb_tmdb.data.remote.toDomain
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val NETWORK_PAGE_SIZE = 20

class MovieRepository(
    private val api: TmdbApi,
) {

    fun popularMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    api = api,
                )
            },
        ).flow
            .map { pagingData -> pagingData.map(MovieDto::toDomain) }
    }
}

