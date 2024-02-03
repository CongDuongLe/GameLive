package com.cdle.gamelive.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cdle.gamelive.data.remote.anime.toAnime
import com.cdle.gamelive.domain.repository.AnimeRepository
import com.cdle.gamelive.domain.models.Anime
import okio.IOException
import retrofit2.HttpException

class AnimePagingSource(
    private val animeRepository: AnimeRepository
) : PagingSource<Int, Anime>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val startKey = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = animeRepository.getTopAnime(
                page = startKey,
                limit = params.loadSize
            )
            val nextKey = if (response.data.isEmpty()) null else response.pagination.hasNextPage
            LoadResult.Page(
                data = response.data.map {
                    it.toAnime(response.pagination.currentPage)
                },
                prevKey = if (startKey == STARTING_PAGE_INDEX) null else startKey - 1,
                nextKey = if (nextKey == null) null else startKey + 1
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
