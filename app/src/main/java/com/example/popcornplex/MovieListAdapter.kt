package com.example.popcornplex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Result> = emptyList()

    fun submitList(movies: List<Result>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        private val movieOverview: TextView = itemView.findViewById(R.id.movie_overview)
        private val moviePoster: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bind(movie: Result) {
            movieTitle.text = movie.title
            movieOverview.text = movie.overview
            val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Picasso.get().load(posterUrl).into(moviePoster)
        }
    }
}
