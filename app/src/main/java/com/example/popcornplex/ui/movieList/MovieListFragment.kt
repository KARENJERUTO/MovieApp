package com.example.popcornplex.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popcornplex.adapter.MovieAdapter
import com.example.popcornplex.R
import com.example.popcornplex.data.Result

class MovieListFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.movies.observe(viewLifecycleOwner, Observer<List<Result>> { movies ->
            movies?.let {
                movieAdapter.submitList(it)
            }
        })

        movieViewModel.fetchMovies("b5c3b9807101bcd9c04917c917742d2d")
    }
}
