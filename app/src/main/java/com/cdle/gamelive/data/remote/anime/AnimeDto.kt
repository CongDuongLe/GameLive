package com.cdle.gamelive.data.remote.anime


import com.squareup.moshi.Json

data class AnimeDto(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "pagination")
    val pagination: Pagination
)
