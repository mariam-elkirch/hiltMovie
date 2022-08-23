package com.example.hiltmovies.network

import com.example.hiltmovies.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("movie?")
    suspend fun getAllMovies(): Response<MoviesResponse>

}