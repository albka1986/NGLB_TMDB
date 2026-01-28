package com.ponomarenko.nglb_tmdb.di

import com.ponomarenko.nglb_tmdb.data.repository.MovieRepositoryImpl
import com.ponomarenko.nglb_tmdb.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}