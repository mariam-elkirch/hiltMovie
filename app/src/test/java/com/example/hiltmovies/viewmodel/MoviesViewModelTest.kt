package com.example.hiltmovies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hiltmovies.MainCoroutineRule
import com.example.hiltmovies.displayMovie.viewmodel.MoviesViewModel
import com.example.hiltmovies.getOrAwaitValueTest
import com.example.hiltmovies.getOrAwaitValueTest
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.MoviesRepositoryInterface
import com.example.hiltmovies.repo.FakeRepository
import com.example.pockomen.favourite.viewmodel.FavouriteViewModel
import com.google.ar.core.Config
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)

class MoviesViewModelTest:TestCase()  {
    private lateinit var moviesViewModel: MoviesViewModel
   val repository = FakeRepository()
    private val favourites = mutableListOf<Favourite>()
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainRule = MainCoroutineRule()
    @Before
    fun obtainViewModelInstance() {

       moviesViewModel = MoviesViewModel(repository)

    }

    @Test
    fun getAllMoviesTest(){
     assertEquals(1, moviesViewModel.responseTvShow.getOrAwaitValueTest().size)

    }

    @Test
    fun insertFavTest(){
        val exampleFavourite = Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")

        favourites.add(exampleFavourite)
        moviesViewModel.insertFav(exampleFavourite)
       assertThat(repository.getAllFavourite().value).contains(exampleFavourite)
    }
}