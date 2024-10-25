package com.example.cinema.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService: MoviesApiService by lazy {
        MoviesApiServiceImpl(retrofit)
    }
}

