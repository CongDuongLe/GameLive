package com.cdle.gamelive.domain.models

import com.cdle.gamelive.data.remote.anime.*

// each Anime Item looks like this
data class Anime(
    val malId: Int? = null,
    val url: String? = null,
    val images: Images? = null,
    val approved: Boolean? = null,
    val titleEnglish: String? = null,
    val titleJapanese: String? = null,
    val type: String? = null,
    val source: String? = null,
    val episodes: Int? = null,
    val status: String? = null,
    val duration: String? = null,
    val rating: String? = null,
    val score: Double? = null,
    val rank: Int? = null,
    val synopsis: String? = null,
    val season: String? = null,
    val year: Int? = null,
    val currentPage: Int? = null,
)


