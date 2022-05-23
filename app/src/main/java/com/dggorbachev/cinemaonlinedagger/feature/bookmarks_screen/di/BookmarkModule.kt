package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.di

import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.BookmarksRepoImpl
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local.BookmarksDAO
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.domain.BookmarksInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookmarkModule {

    @Provides
    @Singleton
    fun provideTrailersRepo(bookmarksDAO: BookmarksDAO): BookmarksRepo =
        BookmarksRepoImpl(bookmarksDAO = bookmarksDAO)

    @Provides
    @Singleton
    fun provideTrailersInteractor(repo: BookmarksRepo): BookmarksInteractor =
        BookmarksInteractor(repo = repo)
}