package com.example.hiltmovies.network

import com.example.hiltmovies.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("movie?api_key=bdee2da50a6d74db54386e74ecb18c4f")
    suspend fun getAllMovies(): Response<MoviesResponse>

}