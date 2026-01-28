package com.ponomarenko.nglb_tmdb.di

import com.ponomarenko.nglb_tmdb.ui.viewmodel.MainViewModel
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { MovieDetailsViewModel(get()) }
}
