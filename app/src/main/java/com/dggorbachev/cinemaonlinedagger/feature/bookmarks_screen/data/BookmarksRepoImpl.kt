package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local.BookmarksDAO
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import javax.inject.Inject

class BookmarksRepoImpl
@Inject constructor(private val bookmarksDAO: BookmarksDAO) : BookmarksRepo {
    override suspend fun create(movie: Movie) {
        bookmarksDAO.create(movie.toEntityModel())
    }

    override suspend fun read(): List<Movie> {
        return bookmarksDAO.read().map { it.toDomainModel() }
    }

    override suspend fun update(movie: Movie) {
        bookmarksDAO.update(movie.toEntityModel())
    }

    override suspend fun delete(movie: Movie) {
        bookmarksDAO.delete(movie.toEntityModel())
    }

    override suspend fun subscribe(): LiveData<List<Movie>> {
        return bookmarksDAO.subscribe().map { it -> it.map { it.toDomainModel() } }
    }
}