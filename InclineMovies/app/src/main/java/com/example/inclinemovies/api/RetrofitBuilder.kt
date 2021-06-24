package com.example.inclinemovies.api

import com.example.inclinemovies.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitBuilder {

    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create()).build()

    val api: MoviesInterface = retrofit.create(MoviesInterface::class.java)
}