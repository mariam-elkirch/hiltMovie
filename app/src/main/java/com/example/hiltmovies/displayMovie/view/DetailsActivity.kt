package com.example.hiltmovies.displayMovie.view

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.hiltmovies.R
import com.example.hiltmovies.displayMovie.view.ui.theme.HiltMoviesTheme
import com.example.hiltmovies.displayMovie.viewmodel.MoviesViewModel
import com.example.hiltmovies.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    private lateinit var viewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
     val   movie = intent.getSerializableExtra("EXTRA_RESULT") as? Result

        setContent {
            HiltMoviesTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    CharacterImageCard(movie!!)
                }
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterImageCard(movie:Result) {
    val imagerPainter =
        rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}")

    Card(

        modifier = Modifier.padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column {

            Image(
                painter = imagerPainter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillBounds
            )
            RatingBar(rating = movie.vote_average/2)

            Text(text = movie.title, fontWeight = FontWeight.Bold,fontSize = 25.sp)
            Text(text = "Release Date: ${movie.release_date}", color = Color.Gray)
            Text(text = movie.overview,Modifier.padding(top = 20.dp),fontSize = 16.sp)


        }
    }}
@Composable
fun Greeting(movieName: String) {
    Text(text = "Hello ${movieName}!")
}
@ExperimentalComposeUiApi
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double
) {
    var ratingState by remember {
        mutableStateOf(rating)
    }

    var selected by remember {
        mutableStateOf(false)
    }
    val size by animateDpAsState(
        targetValue = if (selected) 62.dp else 54.dp,
        spring(Spring.DampingRatioMediumBouncy)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),


        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_rate_24),
                contentDescription = "star",
                modifier = modifier
                    .width(size)
                    .height(size),

                tint = if (i <= ratingState) Color(0xFFFFD700) else Color(0xFFA2ADB1)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HiltMoviesTheme {
        Greeting("Spider Man")
    }
}