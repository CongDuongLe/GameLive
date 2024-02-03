package com.cdle.gamelive.data.remote.game

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApi {


    @GET("games")
    //?page=1&page_size=2&key=1f9af45179964ba7bd4c4140a0b336b4
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("key") key: String = API_KEY,

        ): GameDto


    // get game detail
    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") key: String = API_KEY,

        ): GameDetailDto


    // path is base_url/games/{id}/screenshots
    @GET("games/{id}/screenshots")
    suspend fun getGameScreenshots(
        @Path("id") id: Int,
        @Query("key") key: String = API_KEY,
    ): GameScreenshotDto


    companion object {
        const val BASE_URL = "https://api.rawg.io/api/"
        const val API_KEY = "1f9af45179964ba7bd4c4140a0b336b4"
    }
}
