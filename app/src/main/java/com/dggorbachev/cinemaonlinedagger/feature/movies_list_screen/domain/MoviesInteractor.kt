package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain

import com.dggorbachev.cinemaonlinedagger.base.attempt
import com.dggorbachev.cinemaonlinedagger.base.mapToList
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.BookmarksRepo
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRepo
import javax.inject.Inject

class MoviesInteractor
@Inject constructor(val moviesRepo: MoviesRepo, val bookmarksRepo: BookmarksRepo) {
    suspend fun getMovies() = attempt {
        mapToList(moviesRepo.getMovies(), bookmarksRepo.read())
    }
}