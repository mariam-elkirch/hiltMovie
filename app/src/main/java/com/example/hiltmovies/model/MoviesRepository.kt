package com.example.hiltmovies.model

import androidx.lifecycle.LiveData
import com.example.hiltmovies.DataBase.MovieDao
import com.example.hiltmovies.network.ApiService
import javax.inject.Inject

class MoviesRepository
@Inject
constructor(private val apiService: ApiService, var movieDao: MovieDao) {
    suspend fun getAllMovies() = apiService.getAllMovies()

    suspend fun insertFavourite(favourite: Favourite) {
        movieDao.insertAllFav(favourite)
    }
    fun getAllFavourite(): LiveData<List<Favourite>> {
        return  movieDao.getAllFavourite()
    }
    suspend fun deleteFav(item: Favourite) {
        movieDao.deleteFav(item)
    }
}