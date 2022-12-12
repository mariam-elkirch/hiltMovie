package com.example.hiltmovies.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.MoviesRepositoryInterface
import com.example.hiltmovies.model.MoviesResponse
import com.example.hiltmovies.model.Result
import junit.framework.TestCase
import retrofit2.Response

class FakeRepository:MoviesRepositoryInterface{
    private val favourites = mutableListOf<Favourite>()
    private  val movies= mutableListOf(
        Result(overview = "The Red Ribbon Army, an evil organization that was once destroyed by Goku in the past, has been reformed by a group of people who have created new and mightier Androids, Gamma 1 and Gamma 2," +
                " and seek vengeance against Goku and his family.",
            vote_average = 7.6, poster_path = "/rugyJdeoJm7cSJL1q4jBpTNbxyU.jpg"
            , release_date = "2022-06-11", title = "Dragon Ball Super: Super Hero"
            , adult = false, backdrop_path = "/ugS5FVfCI3RV0ZwZtBV3HAV75OX.jpg", genre_ids =listOf(16,28), id = 610150,
            original_language = "ja", video = false, vote_count = 144
            , original_title = "ドラゴンボール超 スーパーヒーロー", popularity = 7949.533)
    )
    private  val favouriteLiveData = MutableLiveData<List<Favourite>>(favourites)
   override suspend fun getAllMovies(): Response<MoviesResponse> = Response.success(MoviesResponse(page = 1, results = movies, total_pages = 34847, total_results = 34847))
    override suspend fun insertFavourite(favourite: Favourite) {
       favourites.add(favourite)
    }
    override fun getAllFavourite(): LiveData<List<Favourite>> {
        return  favouriteLiveData
    }
    override suspend fun deleteFav(favourite: Favourite) {
       favourites.remove(favourite)
    }
}