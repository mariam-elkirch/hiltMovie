package com.example.hiltmovies.displayMovie.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.MoviesRepository
import com.example.hiltmovies.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel
@Inject
constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _response = MutableLiveData<List<Result>>()
    val responseTvShow: LiveData<List<Result>>
        get() = _response

    init {
        getAllMovies()
    }

    private fun getAllMovies() = viewModelScope.launch {
        repository.getAllMovies().let {response ->

            if (response.isSuccessful){
                _response.postValue(response.body()!!.results)
            }else{
                Log.d("tag", "getAllTvShows Error: ${response.code()}")
            }
        }
    }
    fun insertFav(favourite: Favourite) {
        viewModelScope.launch(Dispatchers.IO){

           repository.insertFavourite(favourite)
        }
    }


}
