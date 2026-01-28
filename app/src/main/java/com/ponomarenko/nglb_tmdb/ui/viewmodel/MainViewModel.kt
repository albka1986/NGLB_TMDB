package com.ponomarenko.nglb_tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ponomarenko.nglb_tmdb.domain.model.Movie
import com.ponomarenko.nglb_tmdb.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

    val popularMovies: Flow<PagingData<Movie>> =
        getMoviesUseCase()
            .cachedIn(viewModelScope)
}
