package com.ponomarenko.nglb_tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ponomarenko.nglb_tmdb.data.remote.MovieDto
import com.ponomarenko.nglb_tmdb.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    movieRepository: MovieRepository,
) : ViewModel() {

    val popularMovies: Flow<PagingData<MovieDto>> =
        movieRepository
            .popularMovies()
            .cachedIn(viewModelScope)
}