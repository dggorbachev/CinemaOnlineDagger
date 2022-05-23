package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.domain

import androidx.lifecycle.LiveData
import com.dggorbachev.cinemaonlinedagger.base.attempt
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

class BookmarksInteractor(private val repo: BookmarksRepo) {
    suspend fun create(movie: Movie) =
        repo.create(movie = movie)

    suspend fun read(): List<Movie> = repo.read()
    suspend fun update(movie: Movie) =
        repo.update(movie = movie)

    suspend fun delete(movie: Movie) =
        attempt { repo.delete(movie = movie) }
}