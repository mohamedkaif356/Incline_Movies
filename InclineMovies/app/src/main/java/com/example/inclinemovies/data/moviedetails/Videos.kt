package com.example.inclinemovies.data.moviedetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Videos(
    @Json(name = "results")
    val result: List<Result>
)