package com.example.inclinemovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.inclinemovies.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(AllMoviesViewModel::class.java)

        val fetchMovies = findViewById<Button>(R.id.fetch_movies)

        fetchMovies.setOnClickListener {
            viewModel.fetchPopularMovies()
        }
    }
}