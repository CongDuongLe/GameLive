package com.cdle.gamelive.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdle.gamelive.domain.repository.GameDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameRepository: GameDetailRepository
) : ViewModel() {
    private val _gameDetail = MutableStateFlow<GameDetailState?>(GameDetailState.Empty)
    private val _gameScreenshot = MutableStateFlow<GameScreenshotState?>(GameScreenshotState.Empty)
    val gameDetail: StateFlow<GameDetailState?> = _gameDetail

    val gameScreenshot: StateFlow<GameScreenshotState?> = _gameScreenshot

    fun getGameDetail(id: Int) {
        _gameDetail.value = GameDetailState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val gameDetailDto = gameRepository.getGameDetail(id)
                _gameDetail.value = gameDetailDto?.let { GameDetailState.Success(it) }
            } catch (e: HttpException) {
                // Handle error, log, or show an error state
                _gameDetail.value = GameDetailState.Error("No Internet Connection")
            } catch (e: IOException) {
                // Handle error, log, or show an error state
                _gameDetail.value = GameDetailState.Error("Something went wrong")
            }
        }
    }

    fun getGameScreenshots(id: Int) {
        _gameScreenshot.value = GameScreenshotState.Loading
        viewModelScope.launch {
            try {
                val gameScreenshotDto = gameRepository.getGameScreenshots(id)
                _gameScreenshot.value = gameScreenshotDto?.let { GameScreenshotState.Success(it) }
            } catch (e: HttpException) {
                // Handle error, log, or show an error state
                _gameScreenshot.value = GameScreenshotState.Error("No Internet Connection")
            } catch (e: IOException) {
                // Handle error, log, or show an error state
                _gameScreenshot.value = GameScreenshotState.Error("Something went wrong")
            }
        }
    }


}









