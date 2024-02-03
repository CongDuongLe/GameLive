package com.cdle.gamelive.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cdle.gamelive.data.remote.game.toGame
import com.cdle.gamelive.domain.repository.GameRepository
import com.cdle.gamelive.domain.models.Game
import okio.IOException
import retrofit2.HttpException

class GamePagingSource(
    private val gameRepository: GameRepository
) : PagingSource<Int, Game>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val startKey = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = gameRepository.getTopGames(
                page = startKey,
                limit = params.loadSize
            )
            val nextKey = if (response.results.isEmpty()) null else response.next
            LoadResult.Page(
                data = response.results.map {
                    val hasNextPage = response.next != null
                    it.toGame(
                        hasNextPage
                    )
                },
                prevKey = if (startKey == STARTING_PAGE_INDEX) null else startKey - 1,
                nextKey = if (nextKey == null) null else startKey.plus(1)
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (
            e: HttpException
        ) {
            LoadResult.Error(e)
        }
    }


}
