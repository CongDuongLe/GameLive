package com.cdle.gamelive.data.repository

import com.cdle.gamelive.data.remote.game.GameDto

interface GameRepository {
    suspend fun getTopGames(
        page: Int,
        limit: Int
    ): GameDto
}
