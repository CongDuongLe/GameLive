package com.cdle.gamelive.di

import com.cdle.gamelive.data.remote.anime.AnimeApi
import com.cdle.gamelive.data.remote.game.GameApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NeworkModule {
    private val moshi = Moshi.Builder().add(
        KotlinJsonAdapterFactory()
    ).build()


    private class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val accessToken = "abcxyz"
            // in case have authorization token
            val newRequest = request.newBuilder().addHeader("Authorization", "Bearer ${accessToken}").build()

            val response = chain.proceed(request)

            // Log the request URL
            println("API Request: ${request.url}")
            return response
        }
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Change the logging level as needed
    }

    // Create an instance of LoggingInterceptor
    private val customInterceptor = LoggingInterceptor()
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(customInterceptor)
        .build()


    @Provides
    @Singleton
    fun providesGameApi(): GameApi {
        return Retrofit.Builder()
            .baseUrl(GameApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(
                GameApi::class.java
            )
    }


    @Provides
    @Singleton
    fun providesAnimeApi(): AnimeApi {
        return Retrofit.Builder()
            .baseUrl(AnimeApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(
                AnimeApi::class.java
            )
    }
}
