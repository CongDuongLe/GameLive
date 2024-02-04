package com.cdle.gamelive.data.repositoryImpl

import com.cdle.gamelive.data.remote.anime.AnimeApi
import com.cdle.gamelive.data.remote.anime.AnimeDto
import com.cdle.gamelive.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {
    override suspend fun getTopAnime(page: Int, limit: Int): AnimeDto {
        return withContext(Dispatchers.Default) {
            animeApi.getTopAnime(page, limit)
        }
    }

}
