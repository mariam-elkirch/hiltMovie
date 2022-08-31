package com.example.hiltmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hiltmovies.DataBase.DataBase
import com.example.hiltmovies.DataBase.MovieDao
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.MoviesRepository
import com.example.hiltmovies.network.ApiService
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepositoryTest {
    private val repo = mockk<MoviesRepository>()

    @Test
    fun getAllMoviesTesting() {


       coEvery { repo.getAllMovies().body()?.results?.size } returns 20

    }
    @Test
    fun insertFavouriteTesting() {
        val exampleFavourite =Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        coEvery { repo.insertFavourite(exampleFavourite) }

       every {repo.getAllFavourite().value?.contains(exampleFavourite) }returns true


    }
    @Test
    fun deleteFavTesting() {
        val exampleFavourite =Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        coEvery { repo.deleteFav(exampleFavourite) }

       every{repo.getAllFavourite().value?.contains(exampleFavourite) }returns false


    }
    @Test
    fun getAllFavouriteTesting() {
        val exampleFavourite =Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        coEvery { repo.insertFavourite(exampleFavourite) }

        every {repo.getAllFavourite().value?.contains(exampleFavourite) }returns true


    }
}