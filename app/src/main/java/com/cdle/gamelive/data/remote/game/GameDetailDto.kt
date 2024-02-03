package com.cdle.gamelive.data.remote.game

import com.squareup.moshi.Json

data class GameDetailDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name="background_image")
    val backgroundImage: String,
    @Json(name="rating")
    val rating: Double,
    @Json(name="description")
    val description: String,
    @Json(name="released")
    val released: String,
    @Json(name="playtime")
    val playtime: Int?,
    @Json(name="website")
    val website: String?,
)


data class GameScreenshotDto(
    @Json(name = "count")
    val count: Int,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name ="results")
    val results: List<GameScreenshotResultDto>
)

data class GameScreenshotResultDto(
    @Json(name = "id")
    val id: Int,
    @Json(name="image")
    val image: String,
    @Json(name="width")
    val width: Int,
    @Json(name="height")
    val height: Int,
)

