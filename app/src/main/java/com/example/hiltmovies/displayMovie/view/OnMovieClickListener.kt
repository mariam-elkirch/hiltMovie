package com.example.hiltmovies.displayMovie.view

import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.Result

interface OnMovieClickListener {
    fun onClick(movie : Result)
}