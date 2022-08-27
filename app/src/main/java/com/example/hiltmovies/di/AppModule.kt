package com.example.hiltmovies.di

import com.example.hiltmovies.DataBase.MovieDao
import com.example.hiltmovies.model.MoviesRepository
import com.example.hiltmovies.model.MoviesRepositoryInterface
import com.example.hiltmovies.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectNormalRepo(api:ApiService,dao:MovieDao) = MoviesRepository(api,dao) as MoviesRepositoryInterface
}