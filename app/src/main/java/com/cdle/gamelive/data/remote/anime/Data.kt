package com.cdle.gamelive.data.remote.anime


import com.cdle.gamelive.domain.models.Anime
import com.squareup.moshi.Json

data class Data(
    @Json(name = "approved")
    val approved: Boolean?, // true
    @Json(name = "duration")
    val duration: String?, // 24 min per ep
    @Json(name = "episodes")
    val episodes: Int?, // 28
    @Json(name = "images")
    val images: Images?,
    @Json(name = "mal_id")
    val malId: Int?, // 52991
    @Json(name = "rank")
    val rank: Int?, // 1
    @Json(name = "rating")
    val rating: String?, // PG-13 - Teens 13 or older
    @Json(name = "score")
    val score: Double?, // 9.13 159457
    @Json(name = "season")
    val season: String?, // fall
    @Json(name = "source")
    val source: String?, // Manga
    @Json(name = "status")
    val status: String?, // Currently Airing
    @Json(name = "synopsis")
    val synopsis: String?, // During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them.However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her "usual" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through.As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.[Written by MAL Rewrite]
    @Json(name = "title_english")
    val titleEnglish: String?, // Frieren: Beyond Journey's End
    @Json(name = "title_japanese")
    val titleJapanese: String?, // 葬送のフリーレン
    @Json(name = "type")
    val type: String?, // TV
    @Json(name = "url")
    val url: String?, // https://myanimelist.net/anime/52991/Sousou_no_Frieren
    @Json(name = "year")
    val year: Int? // 2023
)


fun Data.toAnime(
    currentPage: Int
): Anime {
    return Anime(
        malId = malId,
        url = url,
        images = images,
        approved = approved,
        titleEnglish = titleEnglish,
        titleJapanese = titleJapanese,
        type = type,
        source = source,
        episodes = episodes,
        status = status,
        duration = duration,
        rating = rating,
        score = score,
        rank = rank,
        synopsis = synopsis,
        season = season,
        year = year,
        currentPage = currentPage
    )
}
