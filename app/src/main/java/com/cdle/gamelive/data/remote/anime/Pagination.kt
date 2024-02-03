package com.cdle.gamelive.data.remote.anime


import com.squareup.moshi.Json

data class Pagination(
    @Json(name = "current_page")
    val currentPage: Int, // 1
    @Json(name = "has_next_page")
    val hasNextPage: Boolean, // true
    @Json(name = "items")
    val items: Items,
    @Json(name = "last_visible_page")
    val lastVisiblePage: Int // 26279
)
