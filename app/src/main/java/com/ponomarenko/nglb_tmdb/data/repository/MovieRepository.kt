package com.ponomarenko.nglb_tmdb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ponomarenko.nglb_tmdb.data.remote.MovieDto
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi
import kotlinx.coroutines.flow.Flow

private const val NETWORK_PAGE_SIZE = 20

class MovieRepository(
    private val api: TmdbApi,
    private val apiKey: String,
) {

    fun popularMovies(): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    api = api,
                    apiKey = apiKey,
                )
            },
        ).flow
    }
}

