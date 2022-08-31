package com.example.hiltmovies.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.hiltmovies.DataBase.DataBase
import com.example.hiltmovies.DataBase.MovieDao
import com.example.hiltmovies.getOrAwaitValue
import com.example.hiltmovies.model.Favourite
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database : DataBase

    private lateinit var dao : MovieDao

    @Before
    fun setup() {

        hiltRule.inject()
        dao = database.movieDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertMovieTesting() = runTest {
        val exampleFavourite =Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        dao.insertAllFav(exampleFavourite)

        val list = dao.getAllFavourite().getOrAwaitValue()
        assertThat(list).contains(exampleFavourite)

    }

    @Test
    fun deleteMovieTesting() = runTest {
        val exampleFavourite = Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        dao.insertAllFav(exampleFavourite)
        dao.deleteFav(exampleFavourite)

        val list = dao.getAllFavourite().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleFavourite)

    }
    @Test
    fun getFavouriteTesting() = runTest {
        val exampleFavourite = Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        dao.insertAllFav(exampleFavourite)

        val list = dao.getAllFavourite().getOrAwaitValue()
        assertThat(list).contains(exampleFavourite)

    }

}