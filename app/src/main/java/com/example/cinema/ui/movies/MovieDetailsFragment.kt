package com.example.cinema.ui.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cinema.R
import com.example.cinema.data.Movie
import com.example.cinema.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("MovieDetailsFragment", "Creating view for MovieDetailsFragment")

        // Получаем данные переданного фильма
        val movie = requireArguments().getParcelable<Movie>("movie")
        if (movie != null) {
            Log.d("MoviesDetailsFragment", "Movie received: ${movie.localized_name}")
            displayMovieDetails(movie)
        } else {
            Log.e("MoviesDetailsFragment", "No movie data received in arguments")
        }
    }

    @SuppressLint("ResourceType", "SetTextI18n")
    private fun displayMovieDetails(movie: Movie) {
        Log.d("MovieDetailsFragment", "Displaying details for movie: ${movie.localized_name}")

        // Установка названия фильма
        binding.tvMovieName.text = movie.localized_name ?: "Название не доступно"

        // Установка года и жанров
        val genresText = if (movie.genres != null && movie.genres.isNotEmpty()) {
            movie.genres.joinToString(", ")
        } else {
            "Жанры не указаны"
        }
        binding.tvMovieYearAndGenres.text = "${movie.year ?: "Год не указан"}, $genresText"

        // Установка рейтинга
        binding.tvMovieRating.text = movie.rating?.toString() ?: "Рейтинг не указан"

        // Установка описания фильма
        binding.tvMovieDescription.text = movie.description ?: "Описание не доступно"

        // Загрузка изображения постера с обработкой ошибки
        Glide.with(binding.root.context)
            .load(movie.image_url)
            .error(R.drawable.error_image)  // Показать изображение ошибки
            .into(binding.ivMoviePoster)

        // Проверка на случай, если у фильма нет постера
        if (movie.image_url.isNullOrEmpty()) {
            binding.ivMoviePoster.setImageResource(R.drawable.error_image)
        }
    }


}






