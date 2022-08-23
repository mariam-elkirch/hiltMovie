package com.example.pockomen.favourite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject
constructor(private val repository: MoviesRepository) : ViewModel() {
    fun getAllFav(): LiveData<List<Favourite>> {
        return repository.getAllFavourite()
    }

    fun deleteFav(favourite: Favourite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFav(favourite)
        }
    }
}