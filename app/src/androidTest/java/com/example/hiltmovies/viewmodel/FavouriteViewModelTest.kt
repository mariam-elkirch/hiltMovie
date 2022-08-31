package com.example.hiltmovies.viewmodel

import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.Result
import com.example.pockomen.favourite.viewmodel.FavouriteViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class FavouriteViewModelTest {
    val favouriteViewModel = mockk<FavouriteViewModel>()

    private val favourites = mutableListOf<Favourite>()

    @Before
    fun setup() {

        every {favouriteViewModel.getAllFav().value} returns favourites
    }
    @Test
    fun getAllFavTest(){
        val exampleFavourite = Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        favourites.add(exampleFavourite)
        Truth.assertThat(favouriteViewModel.getAllFav().value).contains(exampleFavourite)
    }
    @Test
    fun deleteFavTest(){
        val exampleFavourite = Favourite(title = "Top Gun: Maverick", id = 361743, poster_path = "/62HCnUTziyWcpDaBO2i1DX17ljH.jpg")
        favourites.add(exampleFavourite)
        coEvery { favouriteViewModel.deleteFav(exampleFavourite)  }
        favourites.remove(exampleFavourite)
        Truth.assertThat(favouriteViewModel.getAllFav().value).doesNotContain(exampleFavourite)
    }


}