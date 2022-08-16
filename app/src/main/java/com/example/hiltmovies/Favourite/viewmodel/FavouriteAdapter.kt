package com.example.hiltmovies.Favourite.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiltmovies.databinding.MovieItemBinding
import com.example.hiltmovies.model.Favourite


class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var moviesList = mutableListOf<Favourite>()

    fun setData(moviesList: List<Favourite>) {
        this.moviesList = moviesList.toMutableList()
        Log.i("tag","Adapter"+moviesList.toString()+"Adapter")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun getItemCount() = moviesList.size
    fun getMovieByPosition(position :Int) :Favourite {
        return moviesList.get(position)
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.MyViewHolder, position: Int) {
        val movie = moviesList.get(position)
        holder.binding.apply {
            textView.text = movie.title
            Glide.with(holder.binding.textView.context).load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .into(holder.binding.imageView)
        }
    }
}