package com.example.hiltmovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class Favourite(
     @PrimaryKey(autoGenerate = true)
    val id: Int,
     val poster_path: String,
     val title: String,

    )