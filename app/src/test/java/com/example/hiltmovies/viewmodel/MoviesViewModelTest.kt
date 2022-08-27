package com.example.hiltmovies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hiltmovies.MainCoroutineRule
import com.example.hiltmovies.displayMovie.viewmodel.MoviesViewModel
import com.example.hiltmovies.getOrAwaitValueTest
import com.example.hiltmovies.getOrAwaitValueTest
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.repo.FakeRepository
import com.google.ar.core.Config

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//@HiltAndroidTest
class MoviesViewModelTest:TestCase()  {
    private lateinit var moviesViewModel: MoviesViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainRule = MainCoroutineRule()
    @Before
    fun obtainViewModelInstance() {
         moviesViewModel = MoviesViewModel(repository = FakeRepository())

    }

    @Test
    fun getAllMovies(){
     assertEquals(1, moviesViewModel.responseTvShow.getOrAwaitValueTest().size)

    }

    @Test
    fun insertFav(){

      //  assertEquals(1, moviesViewModel.responseTvShow.getOrAwaitValueTest().size)

    }
}