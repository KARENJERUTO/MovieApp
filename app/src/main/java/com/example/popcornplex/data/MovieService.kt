package com.example.popcornplex.data

// MovieService.kt
import okhttp3.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

class MovieService {
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val movieListAdapter = moshi.adapter(MovieListResponse::class.java)

    fun fetchMovies(apiKey: String, callback: (MovieListResponse?) -> Unit) {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.let { responseBody ->
                    val json = responseBody.string()
                    val movieListResponse = movieListAdapter.fromJson(json)
                    callback(movieListResponse)
                } ?: callback(null)
            }
        })
    }
}
