package com.cdle.gamelive.di

import com.cdle.gamelive.data.remote.anime.AnimeApi
import com.cdle.gamelive.data.remote.game.GameApi
import com.cdle.gamelive.domain.repository.AnimeRepository
import com.cdle.gamelive.domain.repository.GameDetailRepository
import com.cdle.gamelive.domain.repository.GameRepository
import com.cdle.gamelive.data.repositoryImpl.AnimeRepositoryImpl
import com.cdle.gamelive.data.repositoryImpl.GameRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAnimeRepository(
        animeApi: AnimeApi
    ): AnimeRepository {
        return AnimeRepositoryImpl(
            animeApi = animeApi,
        )
    }

    @Provides
    @Singleton
    fun providesGameRepository(
        gameApi: GameApi,
    ): GameRepository {
        return GameRepositoryImpl(
            gameApi = gameApi,
        )
    }


    @Provides
    @Singleton
    fun providesGameDetailRepository(
        gameApi: GameApi,
    ): GameDetailRepository {
        return GameDetailRepository(
            gameApi = gameApi,
        )
    }
}
