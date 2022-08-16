package com.example.hiltmovies.displayMovie.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltmovies.Favourite.view.FavouriteActivity
import com.example.hiltmovies.displayMovie.viewmodel.MoviesViewModel
import com.example.hiltmovies.databinding.ActivityMainBinding
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val moviesAdapter by lazy { MoviesAdapter() }

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.moviesRecyclerView.adapter = moviesAdapter

        viewModel.responseTvShow.observe(this) { list ->

            moviesAdapter.setData(list)

        }
        binding.ToFavourite.setOnClickListener {
            val intent = Intent(this, FavouriteActivity::class.java)
            startActivity(intent)

        }
        setUpSwipe()
    }
    fun  setUpSwipe(){
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {


                val position = viewHolder.adapterPosition

                val  movie  : Result  =   moviesAdapter.getMovieByPosition(position)
                val favMovie = Favourite(movie.id,movie.poster_path,movie.title)
                viewModel.insertFav(favMovie)
                moviesAdapter.notifyDataSetChanged();
                Toast.makeText(this@MainActivity, "Movie added to database", Toast.LENGTH_SHORT).show();

            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(binding.moviesRecyclerView)
}}