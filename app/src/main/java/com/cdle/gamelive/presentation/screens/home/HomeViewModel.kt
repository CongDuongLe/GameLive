package com.cdle.gamelive.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cdle.gamelive.data.pagination.GamePagingSource
import com.cdle.gamelive.data.repository.GameRepository
import com.cdle.gamelive.domain.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {


    private val config = PagingConfig(
        pageSize = 16,
        initialLoadSize = 16,
        enablePlaceholders = false
    )

    val gameFlow: Flow<PagingData<Game>> = Pager(
        config = config,
        pagingSourceFactory = {
            GamePagingSource(gameRepository)
        }
    ).flow.cachedIn(viewModelScope)

}
