package com.ponomarenko.nglb_tmdb.di

import com.ponomarenko.nglb_tmdb.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
}