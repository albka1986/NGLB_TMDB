package com.ponomarenko.nglb_tmdb.data.remote

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(name = AUTHORIZATION_HEADER_KEY, value = "$AUTHORIZATION_HEADER_VALUE$token")
            .addHeader(name = ACCEPT_HEADER_KEY, value = ACCEPT_HEADER_VALUE)
            .build()

        return chain.proceed(request)
    }

    private companion object {

        const val AUTHORIZATION_HEADER_KEY = "Authorization"
        const val AUTHORIZATION_HEADER_VALUE = "Bearer "
        const val ACCEPT_HEADER_KEY = "Accept"
        const val ACCEPT_HEADER_VALUE = "application/json"
    }
}