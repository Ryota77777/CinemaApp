package com.example.cinema.data

import android.util.Log
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface MoviesApiService {
    @GET("films.json")
    suspend fun getMovies(): Response<MovieListResponse>
}

class MoviesApiServiceImpl(private val retrofit: Retrofit) : MoviesApiService {
    private val api = retrofit.create(MoviesApiService::class.java)

    override suspend fun getMovies(): Response<MovieListResponse> {
        Log.d("MoviesApiServiceImpl", "Fetching movies from API...")
        val response = api.getMovies()

        // Логирование состояния ответа
        Log.d("MoviesApiServiceImpl", "Response received: code = ${response.code()}, message = ${response.message()}")

        return if (response.isSuccessful) {
            val moviesList = response.body()?.films?.map { createMovieFromApiResponse(it) } ?: emptyList()
            Log.d("MoviesApiServiceImpl", "Movies fetched successfully: $moviesList")
            Response.success(MovieListResponse(moviesList))
        } else {
            Log.e("MoviesApiServiceImpl", "Error fetching movies: ${response.code()} ${response.message()}")
            Log.e("MoviesApiServiceImpl", "Response body: ${response.errorBody()?.string()}")
            response // возвращаем оригинальный ответ с ошибкой
        }
    }

    private fun createMovieFromApiResponse(apiResponse: Movie): Movie {
        return Movie(
            id = apiResponse.id,
            localized_name = apiResponse.localized_name ?: null,
            name = apiResponse.name ?: null,
            year = apiResponse.year ?: null,
            rating = apiResponse.rating ?: null,
            image_url = apiResponse.image_url ?: null,
            description = apiResponse.description ?: null,
            genres = apiResponse.genres ?: emptyList()
        )
    }
}



