package com.example.hiltmovies.model

import com.example.hiltmovies.network.ApiService
import javax.inject.Inject

class MoviesRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getAllMovies() = apiService.getAllMovies()
}