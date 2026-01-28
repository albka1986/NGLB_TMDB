package com.ponomarenko.nglb_tmdb.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ponomarenko.nglb_tmdb.BuildConfig
import com.ponomarenko.nglb_tmdb.data.remote.AuthInterceptor
import com.ponomarenko.nglb_tmdb.data.remote.TmdbApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import timber.log.Timber

private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"

val apiModule = module {
    single {
        HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp")
                .d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        AuthInterceptor(
            token = BuildConfig.TMDB_API_KEY,
        )
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
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
}
