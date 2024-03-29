package com.cdle.gamelive.data.remote.game

import com.squareup.moshi.Json

data class GameDto(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name ="results")
    val results: List<GameResultDto>
)









