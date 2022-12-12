package com.example.hiltmovies.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hiltmovies.DataBase.DataBase
import com.example.hiltmovies.DataBase.MovieDao
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.MoviesRepository
import com.example.hiltmovies.model.MoviesRepositoryInterface
import com.example.hiltmovies.model.MoviesResponse
import com.example.hiltmovies.model.Result
import com.example.hiltmovies.network.ApiService
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import io.mockk.*
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RepositoryTest {

    private val repo = mockk<MoviesRepository>()
    private val favourites = mutableListOf<Favourite>()
    val movies= mutableListOf(
        Result(overview = "The Red Ribbon Army, an evil organization that was once destroyed by Goku in the past, has been reformed by a group of people who have created new and mightier Androids, Gamma 1 and Gamma 2," +
                " and seek vengeance against Goku and his family.",
            vote_average = 7.6, poster_path = "/rugyJdeoJm7cSJL1q4jBpTNbxyU.jpg"
            , release_date = "2022-06-11", title = "Dragon Ball Super: Super Hero"
            , adult = false, backdrop_path = "/ugS5FVfCI3RV0ZwZtBV3HAV75OX.jpg", genre_ids =listOf(16,28), id = 610150,
            original_language = "ja", video = false, vote_count = 144
            , original_title = "ドラゴンボール超 スーパーヒーロー", popularity = 7949.533)
    )
    val exampleFavourite =Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
    @Before
    fun setup() {

        every {repo.getAllFavourite().value}returns favourites
    }

    @Test
    fun insertFavouriteTesting() {

       coEvery { repo.insertFavourite(exampleFavourite)  }
        favourites.add(exampleFavourite)
        Truth.assertThat(repo.getAllFavourite().value).contains(exampleFavourite)

    }
    @Test
    fun deleteFavTesting() {
        coEvery { repo.insertFavourite(exampleFavourite)  }
        favourites.add(exampleFavourite)
        coEvery { repo.deleteFav(exampleFavourite)  }
        favourites.remove(exampleFavourite)
        Truth.assertThat(repo.getAllFavourite().value).doesNotContain(exampleFavourite)

    }
    @Test
    fun getAllFavouriteTesting() {
        coEvery { repo.insertFavourite(exampleFavourite)  }
        favourites.add(exampleFavourite)
        Truth.assertThat(repo.getAllFavourite().value).contains(exampleFavourite)

    }

    @Test
    fun getAllMoviesTesting()= runTest {

        coEvery { repo.getAllMovies()}returns  Response.success(MoviesResponse(page = 1, results = movies, total_pages = 34847, total_results = 34847))
        val listSize = repo.getAllMovies().body()?.results?.size
        TestCase.assertEquals(1,listSize )

    }
}