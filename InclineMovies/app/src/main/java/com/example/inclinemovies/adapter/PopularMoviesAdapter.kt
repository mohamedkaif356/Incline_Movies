package com.example.inclinemovies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inclinemovies.R
import com.example.inclinemovies.data.Constants
import com.example.inclinemovies.data.moviesresponse.Result
import com.example.inclinemovies.ui.details.MoviesDetails

class PopularMoviesAdapter(private val context: Context) : RecyclerView.Adapter<PopularMoviesAdapter.MoviesViewHolder>() {


    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val moviePoster = itemView.findViewById<ImageView>(R.id.iv_movie_poster)!!
        val movieDetails = itemView.findViewById<LinearLayout>(R.id.ll_movie_details)!!
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
                LayoutInflater.from(context).inflate(
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
            val intent = Intent(context , MoviesDetails::class.java)
            intent.putExtra("MovieID", movie.id)
            movieDetails.setOnClickListener {
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}