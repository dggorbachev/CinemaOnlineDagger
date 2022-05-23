package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.di

import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MovieApi
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRemoteSource
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRepo
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRepoImpl
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.MoviesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMoviesRemoteSource(movieApi: MovieApi): MoviesRemoteSource =
        MoviesRemoteSource(movieApi = movieApi)

    @Provides
    @Singleton
    fun provideMoviesRepo(source: MoviesRemoteSource): MoviesRepo =
        MoviesRepoImpl(source = source)

    @Provides
    @Singleton
    fun provideMoviesInteractor(
        moviesRepo: MoviesRepo,
        bookmarksRepo: BookmarksRepo
    ): MoviesInteractor =
        MoviesInteractor(moviesRepo = moviesRepo, bookmarksRepo = bookmarksRepo)
}