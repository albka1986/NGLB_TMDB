package com.ponomarenko.nglb_tmdb.di

import com.ponomarenko.nglb_tmdb.data.repository.MovieRepositoryImpl
import com.ponomarenko.nglb_tmdb.domain.repository.MovieRepository
import com.ponomarenko.nglb_tmdb.domain.use_case.GetMovieDetailsUseCase
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MainViewModel
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    factory { GetMovieDetailsUseCase(get()) }
}
