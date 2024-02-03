package com.cdle.gamelive.domain


data class Game(
    val id: Int,
    val slug: String?,
    val name: String?,
    val released: String?,
    val backgroundImage: String?,
    val rating: Double?,
    val ratingCount: Double?,
    val playtime: Int?,
    val hasNextPage: Boolean?
)
// Path: app/src/main/java/com/cdle/gamelive/domain/Game.kt
