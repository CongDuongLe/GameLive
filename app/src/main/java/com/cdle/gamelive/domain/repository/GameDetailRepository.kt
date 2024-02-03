package com.cdle.gamelive.domain.repository

import com.cdle.gamelive.data.remote.game.GameApi
import com.cdle.gamelive.data.remote.game.GameDetailDto
import com.cdle.gamelive.data.remote.game.GameScreenshotDto
import okio.IOException

class GameDetailRepository(private val gameApi: GameApi) {
    suspend fun getGameDetail(id: Int): GameDetailDto? {
        return try {
            gameApi.getGameDetail(id = id)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: Error) {
            e.printStackTrace()
            null
        }

    }

    suspend fun getGameScreenshots(id: Int): GameScreenshotDto? {
        return try {
            gameApi.getGameScreenshots(id = id)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: Error) {
            e.printStackTrace()
            null
        }

    }


}

