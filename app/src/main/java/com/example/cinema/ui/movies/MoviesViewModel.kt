package com.example.cinema.ui.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.data.Movie
import com.example.cinema.data.MoviesApiService
import kotlinx.coroutines.launch

class MoviesViewModel(private val moviesApiService: MoviesApiService) : ViewModel() {
    val movies = MutableLiveData<List<Movie>>()
    val genres = MutableLiveData<List<String>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()

    init {
        fetchMovies()
    }

    fun fetchMovies() {
        isLoading.postValue(true)
        errorMessage.postValue(null)
        viewModelScope.launch {
            try {
                Log.d("MoviesViewModel", "Fetching movies from API")
                val response = moviesApiService.getMovies()
                if (response.isSuccessful) {
                    val moviesList = response.body()?.films ?: emptyList()
                    movies.postValue(moviesList.sortedBy { it.localized_name })
                    Log.d("MoviesViewModel", "Movies fetched: ${moviesList.size}")

                    val allGenres = mutableListOf("Все жанры")
                    allGenres.addAll(moviesList.flatMap { it.genres ?: emptyList() }.distinct())
                    genres.postValue(allGenres)
                    Log.d("MoviesViewModel", "Genres extracted: ${allGenres.size}")
                } else {
                    errorMessage.postValue("Ошибка загрузки данных")
                    Log.d("MoviesViewModel", "Failed to fetch movies, response unsuccessful")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Ошибка подключения сети")
                Log.e("MoviesViewModel", "Error fetching movies: ${e.localizedMessage}", e)
            } finally {
                isLoading.postValue(false)
                Log.d("MoviesViewModel", "Fetch operation completed")
            }
        }
    }
}












