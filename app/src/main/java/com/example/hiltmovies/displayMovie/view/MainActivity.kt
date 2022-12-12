package com.example.hiltmovies.displayMovie.view

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltmovies.Favourite.view.FavouriteActivity
import com.example.hiltmovies.databinding.ActivityMainBinding
import com.example.hiltmovies.displayMovie.viewmodel.MoviesViewModel
import com.example.hiltmovies.model.Favourite
import com.example.hiltmovies.model.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnMovieClickListener{

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var moviesAdapter:MoviesAdapter


    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        moviesAdapter =  MoviesAdapter()
     moviesAdapter.setOnClickListener(
           this
        )
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.moviesRecyclerView.adapter = moviesAdapter

        viewModel.responseTvShow.observe(this) { list ->
            Log.i("tag",list.toString()+"size" )
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

        }).attachToRecyclerView(binding.moviesRecyclerView)
}

    override fun onClick(movie : Result) {

        Log.i("tag",movie.title+"Main Activity")
        val intent = Intent(this, DetailsActivity::class.java)

        intent.putExtra("EXTRA_RESULT",movie)
        startActivity(intent)
    }


}