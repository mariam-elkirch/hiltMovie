package com.example.hiltmovies.displayMovie.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hiltmovies.databinding.MovieItemBinding
import com.example.hiltmovies.model.Result


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var moviesList = mutableListOf<Result>()

    fun setData(moviesList: List<Result>) {
        this.moviesList = moviesList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList.get(position)
        holder.binding.apply {
            textView.text = movie.title
            Glide.with(holder.binding.textView.context).load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                    .into(holder.binding.imageView)
        }
    }
    fun getMovieByPosition(position :Int) : Result {
        return moviesList.get(position)
    }
    override fun getItemCount() = moviesList.size
}