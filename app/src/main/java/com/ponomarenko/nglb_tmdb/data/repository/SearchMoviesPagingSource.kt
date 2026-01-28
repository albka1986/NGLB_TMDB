package com.ponomarenko.nglb_tmdb.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi
import com.ponomarenko.nglb_tmdb.data.remote.dto.MovieDto
import timber.log.Timber

class SearchMoviesPagingSource(
    private val api: TmdbApi,
    private val query: String
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        if (query.isBlank()) {
            return LoadResult.Page(data = emptyList(), prevKey = null, nextKey = null)
        }

        return try {
            val page = params.key ?: 1
            val response = api.searchMovies(query = query, page = page)
            Timber.d("SearchMoviesPagingSource: Loaded page %d", page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Timber.e(e, "SearchMoviesPagingSource: Error loading page")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}