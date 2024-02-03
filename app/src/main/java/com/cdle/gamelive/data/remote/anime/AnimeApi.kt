package com.cdle.gamelive.data.remote.anime

import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): AnimeDto


    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }
}
