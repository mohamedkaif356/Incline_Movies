package com.example.inclinemovies.ui.details

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inclinemovies.R
import com.example.inclinemovies.adapter.PopularMoviesAdapter
import com.example.inclinemovies.adapter.SimilarMoviesAdapter
import com.example.inclinemovies.data.Constants
import com.example.inclinemovies.data.moviedetails.MovieDetails
import com.example.inclinemovies.data.movievideo.Result
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MoviesDetails : AppCompatActivity() {

    private lateinit var similarMoviesAdapter: SimilarMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_details)

        val similarMoviesRV = findViewById<RecyclerView>(R.id.rv_similar_movies)

        similarMoviesAdapter = SimilarMoviesAdapter(this)

        similarMoviesRV.adapter = similarMoviesAdapter

        val viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(DetailsViewModel::class.java)

        val movieID = intent.extras!!.getInt("MovieID")

        viewModel.fetchMovieDetails(movieID)
        viewModel.fetchMovieVideo(movieID)
        viewModel.fetchSimilarMovies(movieID)

        viewModel.movieVideo.observe({lifecycle}){
            playVideo(it)
        }

        viewModel.movieDetails.observe({lifecycle}){
            setUI(it)
        }

        viewModel.similarMovies.observe({lifecycle}){
            similarMoviesAdapter.differ.submitList(it)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setUI(details: MovieDetails?) {
        val poster = findViewById<ImageView>(R.id.iv_inner_poster)
        val title = findViewById<TextView>(R.id.tv_movie_title)
        val type = findViewById<TextView>(R.id.tv_movie_type)
        val date = findViewById<TextView>(R.id.tv_movie_date)
        val description = findViewById<TextView>(R.id.tv_movie_desc)
        val ratings = findViewById<TextView>(R.id.tv_ratings)

        Glide.with(this).load(Constants.IMAGE_URL + details?.posterPath).into(poster)
        title.text = details?.originalTitle
        type.text = details?.genres?.get(0)?.name
        date.text = details?.releaseDate
        description.text = details?.overview
        ratings.text = "Ratings - ${details?.voteAverage}/10"
    }

    private fun playVideo(video: List<Result>?){
        val playTrailer = findViewById<YouTubePlayerView>(R.id.yp_trailer)
        playTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                if (video!![0].key != null){
                    youTubePlayer.loadVideo(video[0].key!!, 0F)
                }

            }
        })
    }
}