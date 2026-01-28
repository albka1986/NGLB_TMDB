package com.ponomarenko.nglb_tmdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ponomarenko.nglb_tmdb.domain.model.MovieDetails
import com.ponomarenko.nglb_tmdb.domain.use_case.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieDetailsViewModel(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {

    private val _movieDetails = MutableStateFlow<Result<MovieDetails>?>(null)
    val movieDetails = _movieDetails.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase(movieId).onEach {
            _movieDetails.value = it
        }.launchIn(viewModelScope)
    }
}
