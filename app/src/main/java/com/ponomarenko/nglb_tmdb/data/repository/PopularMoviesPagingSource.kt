package com.ponomarenko.nglb_tmdb.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ponomarenko.nglb_tmdb.data.remote.MovieDto
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi

class PopularMoviesPagingSource(
    private val api: TmdbApi,
    private val apiKey: String,
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            val page = params.key ?: 1
            val response = api.getPopularMovies(
                apiKey = apiKey,
                page = page,
            )

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1,
            )
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object
}

