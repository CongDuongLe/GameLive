package com.cdle.gamelive.data.remote.anime


import com.squareup.moshi.Json

data class Images(
    @Json(name = "jpg")
    val jpg: Jpg,
    @Json(name = "webp")
    val webp: Webp
)
