package com.example.hiltmovies.displayMovie.view

import com.example.hiltmovies.model.Favourite

interface OnMovieClickListener {
    fun onClick(favourite: Favourite)
}