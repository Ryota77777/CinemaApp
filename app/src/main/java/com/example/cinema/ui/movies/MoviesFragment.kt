package com.example.cinema.ui.movies

import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.cinema.data.Movie
import androidx.fragment.app.Fragment
import com.example.cinema.databinding.FragmentMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.cinema.R
import com.google.android.material.snackbar.Snackbar

class MoviesFragment : Fragment() {
    private val moviesViewModel: MoviesViewModel by viewModel()
    private lateinit var adapter: MovieAdapter
    private lateinit var genresSpinner: Spinner
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        Log.d("MoviesFragment", "onCreateView called")
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        adapter = MovieAdapter { movie ->
            Log.d("MoviesFragment", "Movie clicked: ${movie.name}")
            navigateToMovieDetails(movie)
        }
        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(requireContext(), 2)

        genresSpinner = binding.spinnerGenres

        moviesViewModel.genres.observe(viewLifecycleOwner) { genres ->
            if (!genres.isNullOrEmpty()) {
                Log.d("MoviesFragment", "Genres data updated: ${genres.size} genres received")
                setupGenresSpinner(genres)
            }
        }

        moviesViewModel.movies.observe(viewLifecycleOwner) { movies ->
            Log.d("MoviesFragment", "Movies data updated: ${movies?.size ?: 0} movies received")
            adapter.submitList(movies ?: emptyList())
        }

        moviesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
            Log.d("MoviesFragment", "Loading state changed: $isLoading")
        }

        moviesViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Log.d("MoviesFragment", "Error occurred: $message")
                Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Повторить") {
                        moviesViewModel.fetchMovies() // Повторная загрузка фильмов
                    }
                    .show()
            }
        }

        return binding.root
    }

    private fun setupGenresSpinner(genres: List<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genresSpinner.adapter = adapter

        genresSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedGenre = genres[position]
                Log.d("MoviesFragment", "Genre selected: $selectedGenre")
                filterMoviesByGenre(selectedGenre)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        filterMoviesByGenre("Все жанры") // Отобразить все фильмы по умолчанию
    }

    private fun filterMoviesByGenre(genre: String) {
        val movies = moviesViewModel.movies.value.orEmpty()
        val filteredMovies = if (genre == "Все жанры") {
            movies
        } else {
            movies.filter { it.genres?.contains(genre) == true }
        }
        Log.d("MoviesFragment", "Filtered movies count: ${filteredMovies.size}")
        adapter.submitList(filteredMovies)
    }

    private fun navigateToMovieDetails(movie: Movie) {
        val bundle = Bundle().apply {
            putParcelable("movie", movie)
        }
        Log.d("MoviesFragment", "Navigating to details of movie: ${movie.name}")
        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
    }
}















