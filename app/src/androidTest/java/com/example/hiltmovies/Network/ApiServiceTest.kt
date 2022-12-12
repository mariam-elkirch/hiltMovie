package com.example.hiltmovies.Network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hiltmovies.model.MoviesResponse
import com.example.hiltmovies.model.Result
import com.example.hiltmovies.network.ApiService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
//import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.junit.Test

import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ApiServiceTest {
    val movies= mutableListOf(
        Result(overview = "The Red Ribbon Army, an evil organization that was once destroyed by Goku in the past, has been reformed by a group of people who have created new and mightier Androids, Gamma 1 and Gamma 2," +
                " and seek vengeance against Goku and his family.",
            vote_average = 7.6, poster_path = "/rugyJdeoJm7cSJL1q4jBpTNbxyU.jpg"
            , release_date = "2022-06-11", title = "Dragon Ball Super: Super Hero"
            , adult = false, backdrop_path = "/ugS5FVfCI3RV0ZwZtBV3HAV75OX.jpg", genre_ids =listOf(16,28), id = 610150,
            original_language = "ja", video = false, vote_count = 144
            , original_title = "ドラゴンボール超 スーパーヒーロー", popularity = 7949.533)
    )
    val api = mockk<ApiService>()
@Test
fun aListOf20MoviesReturned() = runTest {

    coEvery { api.getAllMovies()}answers { Response.success(MoviesResponse(page = 1, results = movies, total_pages = 34847, total_results = 34847))}
     val listSize = api.getAllMovies().body()?.results?.size

    TestCase.assertEquals(1,listSize )
}
}