package com.cdle.gamelive.data.repository

import com.cdle.gamelive.data.remote.anime.AnimeDto


interface AnimeRepository {
    suspend fun getTopAnime(
        page: Int,
        limit: Int
    ): AnimeDto
}
