package com.example.hiltmovies.model

import androidx.lifecycle.LiveData
import dagger.Binds
import dagger.Subcomponent
import retrofit2.Response

interface MoviesRepositoryInterface {
    suspend fun getAllMovies(): Response<MoviesResponse>

    suspend fun insertFavourite(favourite: Favourite)

    fun getAllFavourite(): LiveData<List<Favourite>>

    suspend fun deleteFav(item: Favourite)
}