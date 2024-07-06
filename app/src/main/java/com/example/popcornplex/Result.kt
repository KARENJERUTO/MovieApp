package com.example.popcornplex

data class Result(
    val adult: Boolean,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String
)
