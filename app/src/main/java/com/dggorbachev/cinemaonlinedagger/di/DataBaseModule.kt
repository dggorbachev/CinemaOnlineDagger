package com.dggorbachev.cinemaonlinedagger.di

import android.content.Context
import androidx.room.Room
import com.dggorbachev.cinemaonlinedagger.AppDataBase
import com.dggorbachev.cinemaonlinedagger.base.common.Constants.DATA_BASE
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local.BookmarksDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            DATA_BASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: AppDataBase): BookmarksDAO {
        return appDatabase.bookmarksDAO()
    }
}