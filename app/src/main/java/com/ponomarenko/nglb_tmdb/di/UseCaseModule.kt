package com.ponomarenko.nglb_tmdb.di

import com.ponomarenko.nglb_tmdb.domain.use_case.GetMovieDetailsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMovieDetailsUseCase(get()) }
}
