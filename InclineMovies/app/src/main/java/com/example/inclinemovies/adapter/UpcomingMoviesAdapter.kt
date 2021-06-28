package com.example.inclinemovies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inclinemovies.R
import com.example.inclinemovies.data.Constants
import com.example.inclinemovies.data.moviesresponse.Result

class UpcomingMoviesAdapter : RecyclerView.Adapter<UpcomingMoviesAdapter.MoviesViewHolder>() {


    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val moviePoster = itemView.findViewById<ImageView>(R.id.iv_movie_poster)!!
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movies_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.apply {
            Glide.with(itemView).load(Constants.IMAGE_URL + movie.posterPath).into(moviePoster)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}