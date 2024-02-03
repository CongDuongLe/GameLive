package com.cdle.gamelive.di

import com.cdle.gamelive.data.pagination.AnimePagingSource
import com.cdle.gamelive.data.pagination.GamePagingSource
import com.cdle.gamelive.data.repository.AnimeRepository
import com.cdle.gamelive.data.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimePagingSource(
        animeRepository: AnimeRepository
    ): AnimePagingSource {
        return AnimePagingSource(
            animeRepository = animeRepository
        )
    }

    @Provides
    @Singleton
    fun provideGamePagingSource(
        gameRepository: GameRepository
    ): GamePagingSource {
        return GamePagingSource(
            gameRepository = gameRepository
        )
    }


}

