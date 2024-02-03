package com.cdle.gamelive.data.remote.anime


import com.squareup.moshi.Json

data class Items(
    @Json(name = "count")
    val count: Int, // 1
    @Json(name = "per_page")
    val perPage: Int, // 1
    @Json(name = "total")
    val total: Int // 26279
)
