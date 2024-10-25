package com.example.cinema

import android.app.Application
import com.example.cinema.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import android.util.Log

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApplication)
            modules(appModule)
        }
        Log.d("CinemaApplication", "Koin started")
    }
}