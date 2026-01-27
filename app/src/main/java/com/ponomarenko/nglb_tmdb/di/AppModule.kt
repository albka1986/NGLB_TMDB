package com.ponomarenko.nglb_tmdb.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi
import com.ponomarenko.nglb_tmdb.data.repository.MovieRepository
import com.ponomarenko.nglb_tmdb.ui.viewmodel.MainViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
private const val TMDB_API_KEY = "REPLACE_WITH_YOUR_TMDB_API_KEY"

val appModule = module {

    // ViewModels
    viewModel { MainViewModel(movieRepository = get()) }

    // Networking
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .client(get())
            .addConverterFactory(get<Json>().asConverterFactory(contentType))
            .build()
    }

    single<TmdbApi> { get<Retrofit>().create(TmdbApi::class.java) }

    // Repositories
    single {
        MovieRepository(
            api = get(),
            apiKey = TMDB_API_KEY,
        )
    }
}