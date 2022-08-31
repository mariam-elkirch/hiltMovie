package com.example.hiltmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
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

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ApiServiceTest {

@Test
fun aListOf20MoviesReturned()  {
    val api = mockk<ApiService>()

    coEvery { api.getAllMovies().body()?.results?.size } returns 20
}
}