package com.cdle.gamelive.data.remote.anime


import com.squareup.moshi.Json

data class Jpg(
    @Json(name = "image_url")
    val imageUrl: String, // https://cdn.myanimelist.net/images/anime/1015/138006.jpg
    @Json(name = "large_image_url")
    val largeImageUrl: String, // https://cdn.myanimelist.net/images/anime/1015/138006l.jpg
    @Json(name = "small_image_url")
    val smallImageUrl: String // https://cdn.myanimelist.net/images/anime/1015/138006t.jpg
)
