package com.example.cinema.di

import com.example.cinema.data.RetrofitClient
import com.example.cinema.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import android.util.Log

val appModule = module {
    single {
        Log.d("AppModule", "Creating Retrofit service")
        RetrofitClient.retrofitService
    }
    viewModel {
        Log.d("AppModule", "Creating MoviesViewModel")
        MoviesViewModel(get())
    }
}
