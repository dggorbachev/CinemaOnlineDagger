package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.di

import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.TrailersApi
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.TrailersRemoteSource
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.TrailersRepo
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.TrailersRepoImpl
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.TrailersInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrailersModule {

    @Provides
    @Singleton
    fun provideTrailersApi(retrofit: Retrofit): TrailersApi =
        retrofit.create(TrailersApi::class.java)

    @Provides
    @Singleton
    fun provideTrailersRemoteSource(trailersApi: TrailersApi): TrailersRemoteSource =
        TrailersRemoteSource(trailersApi)

    @Provides
    @Singleton
    fun provideTrailersRepo(source: TrailersRemoteSource): TrailersRepo =
        TrailersRepoImpl(source)

    @Provides
    @Singleton
    fun provideTrailersInteractor(repo: TrailersRepo): TrailersInteractor =
        TrailersInteractor(repo)
}