package com.example.inclinemovies.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inclinemovies.R
import com.example.inclinemovies.data.Constants
import com.example.inclinemovies.data.moviesearch.Result
import com.example.inclinemovies.ui.details.MoviesDetails

class SearchMoviesAdapter(private val context: Context)
    : PagingDataAdapter<Result, SearchMoviesAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviePoster = itemView.findViewById<ImageView>(R.id.iv_movie_poster)!!
        val movieDetails = itemView.findViewById<LinearLayout>(R.id.ll_movie_details)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.movies_list,
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = getItem(position)

        holder.apply {
            Glide.with(itemView)
                .load(Constants.IMAGE_URL + movie?.posterPath)
                .into(moviePoster)
            val intent = Intent(context, MoviesDetails::class.java)
            intent.putExtra("MovieID", movie?.id)
            movieDetails.setOnClickListener {
                context.startActivity(intent)
            }
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

}