package com.dggorbachev.cinemaonlinedagger.di

import com.dggorbachev.cinemaonlinedagger.AppDataBase
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local.BookmarksDAO
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create(Router())
    }

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>): Router {
        return cicerone.router
    }
}