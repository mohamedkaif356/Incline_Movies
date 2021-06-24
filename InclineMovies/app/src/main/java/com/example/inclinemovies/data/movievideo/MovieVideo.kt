package com.example.inclinemovies.data.movievideo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieVideo(
    @Json(name = "id")
    val id: Int,
    @Json(name = "results")
    val results: List<Result>
)