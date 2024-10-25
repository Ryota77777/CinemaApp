package com.example.cinema.ui.movies

import android.view.View
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.DataSource
import com.example.cinema.data.Movie
import com.example.cinema.databinding.ItemMovieBinding

class MovieAdapter(private val onMovieClicked: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movies: List<Movie> = emptyList()

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, onMovieClicked: (Movie) -> Unit) {
            binding.tvMovieName.text = movie.localized_name ?: "Название не доступно"

            // Скрываем иконку ошибки по умолчанию
            binding.ivErrorImage.visibility = View.GONE

            if (!movie.image_url.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(movie.image_url)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.ivErrorImage.visibility = View.VISIBLE
                            binding.ivMoviePoster.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            binding.ivErrorImage.visibility = View.GONE
                            binding.ivMoviePoster.visibility = View.VISIBLE
                            return false
                        }
                    })
                    .into(binding.ivMoviePoster)
            } else {
                binding.ivErrorImage.visibility = View.VISIBLE
                binding.ivMoviePoster.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onMovieClicked)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun submitList(movieList: List<Movie>) {
        movies = movieList
        notifyDataSetChanged()
    }
}











