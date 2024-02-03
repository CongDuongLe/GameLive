package com.cdle.gamelive.presentation.screens.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cdle.gamelive.data.pagination.AnimePagingSource
import com.cdle.gamelive.data.repository.AnimeRepository
import com.cdle.gamelive.domain.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject
constructor(
    private val animeRepository: AnimeRepository,
) : ViewModel(
) {
    private val config = PagingConfig(
        pageSize = 16,
        initialLoadSize = 16,
        enablePlaceholders = false
    )

    val animeFlow: Flow<PagingData<Anime>> = Pager(
        config = config,
        pagingSourceFactory = { AnimePagingSource(animeRepository) }
    ).flow.cachedIn(viewModelScope)


}
