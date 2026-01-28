package com.ponomarenko.nglb_tmdb

import android.app.Application
import com.ponomarenko.nglb_tmdb.di.apiModule
import com.ponomarenko.nglb_tmdb.di.appModule
import com.ponomarenko.nglb_tmdb.di.repositoryModule
import com.ponomarenko.nglb_tmdb.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, apiModule, repositoryModule, useCaseModule)
        }
    }
}
