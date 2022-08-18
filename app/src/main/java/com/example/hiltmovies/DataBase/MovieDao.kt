package com.example.hiltmovies.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hiltmovies.model.Favourite


@Dao
public interface MovieDao {
    @Query("SELECT * From favourite")
    fun getAllFavourite(): LiveData<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFav(fav:Favourite)

    @Delete
   suspend fun deleteFav(fav: Favourite)
}