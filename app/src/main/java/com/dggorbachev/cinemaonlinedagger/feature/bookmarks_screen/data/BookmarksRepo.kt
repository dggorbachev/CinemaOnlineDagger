package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data

import androidx.lifecycle.LiveData
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

interface BookmarksRepo {
    suspend fun create(movie: Movie)
    suspend fun read(): List<Movie>
    suspend fun update(movie: Movie)
    suspend fun delete(movie: Movie)
    suspend fun subscribe(): LiveData<List<Movie>>
}