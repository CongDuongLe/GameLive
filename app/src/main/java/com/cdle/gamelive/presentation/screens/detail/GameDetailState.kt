package com.cdle.gamelive.presentation.screens.detail

import com.cdle.gamelive.data.remote.game.GameDetailDto
import com.cdle.gamelive.data.remote.game.GameScreenshotDto


sealed class GameDetailState {
    object Empty : GameDetailState()
    object Loading : GameDetailState()
    data class Success(val data: GameDetailDto) : GameDetailState()
    data class Error(val error: String) : GameDetailState()
}

sealed class GameScreenshotState {
    object Empty : GameScreenshotState()
    object Loading : GameScreenshotState()
    data class Success(val data: GameScreenshotDto) : GameScreenshotState()
    data class Error(val error: String) : GameScreenshotState()
}
