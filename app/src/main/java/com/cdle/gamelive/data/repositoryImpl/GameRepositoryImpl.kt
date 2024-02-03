package com.cdle.gamelive.data.repositoryImpl

import com.cdle.gamelive.data.remote.game.GameApi
import com.cdle.gamelive.data.remote.game.GameDto
import com.cdle.gamelive.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val gameApi: GameApi
) : GameRepository {
    override suspend fun getTopGames(page: Int, limit: Int): GameDto {

        return gameApi.getGames(page, pageSize = limit)
    }
}
