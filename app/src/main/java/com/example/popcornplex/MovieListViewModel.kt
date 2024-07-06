package com.example.popcornplex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    private val movieService = MovieService()
    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> get() = _movies

    fun fetchMovies(apiKey: String) {
        movieService.fetchMovies(apiKey) { movieListResponse ->
            movieListResponse?.results?.let {
                _movies.postValue(it)
            }
        }
    }
}
