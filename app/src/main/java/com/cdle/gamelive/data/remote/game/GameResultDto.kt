package com.cdle.gamelive.data.remote.game

import com.cdle.gamelive.domain.Game
import com.squareup.moshi.Json

data class GameResultDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "released")
    val released: String,
    @Json(name = "background_image")
    val backgroundImage: String,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "rating_count")
    val ratingCount: Double?,
    @Json(name = "playtime")
    val playtime: Int?,
)


fun GameResultDto.toGame(
    hasNextPage: Boolean,
) = Game(
    id = id,
    slug = slug,
    name = name,
    released = released,
    backgroundImage = backgroundImage,
    rating = rating,
    ratingCount = ratingCount,
    playtime = playtime,
    hasNextPage = hasNextPage
)
