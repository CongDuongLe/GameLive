package com.cdle.gamelive.data.repositoryImpl

import com.cdle.gamelive.data.remote.anime.AnimeApi
import com.cdle.gamelive.data.remote.anime.AnimeDto
import com.cdle.gamelive.data.repository.AnimeRepository
import javax.inject.Inject


class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {
    override suspend fun getTopAnime(page: Int, limit: Int): AnimeDto {
        return animeApi.getTopAnime(page, limit)
    }

}
