package com.example.hiltmovies.Favourite.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltmovies.Favourite.viewmodel.FavouriteAdapter
import com.example.hiltmovies.R
import com.example.hiltmovies.databinding.ActivityFavouriteBinding
import com.example.hiltmovies.displayMovie.view.MainActivity
import com.example.hiltmovies.model.Favourite
import com.example.pockomen.favourite.viewmodel.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFavouriteBinding.inflate(layoutInflater) }

    private val favAdapter by lazy { FavouriteAdapter() }

    private lateinit var viewModel: FavouriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.favouriteRecyclerView.adapter = favAdapter



        viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        viewModel.getAllFav().observe(this) {

            favAdapter.setData(it)
        }

        binding.ToHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        setUpSwipe()
    }

    fun setUpSwipe() {
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

                val movie: Favourite = favAdapter.getMovieByPosition(position)
                val favMovie = Favourite(movie.id, movie.poster_path, movie.title)
                viewModel.deleteFav(favMovie)
                favAdapter.notifyDataSetChanged()
                Toast.makeText(this@FavouriteActivity, "Movie deleted", Toast.LENGTH_SHORT).show();

            }

        }).attachToRecyclerView(binding.favouriteRecyclerView)

    }
}