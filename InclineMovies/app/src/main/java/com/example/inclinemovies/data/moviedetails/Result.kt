package com.example.inclinemovies.data.moviedetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "key")
    val key: String
)