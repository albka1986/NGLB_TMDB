package com.ponomarenko.nglb_tmdb.di

import com.ponomarenko.nglb_tmdb.domain.use_case.GetMovieDetailsUseCase
import com.ponomarenko.nglb_tmdb.domain.use_case.GetMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMoviesUseCase(get()) }
    factory { GetMovieDetailsUseCase(get()) }
}