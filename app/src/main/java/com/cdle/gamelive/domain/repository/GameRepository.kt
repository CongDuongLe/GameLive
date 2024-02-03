package com.cdle.gamelive.domain.repository

import com.cdle.gamelive.data.remote.game.GameDto

interface GameRepository {
    suspend fun getTopGames(
        page: Int,
        limit: Int
    ): GameDto
}
