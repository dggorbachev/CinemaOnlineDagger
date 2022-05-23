package com.dggorbachev.cinemaonlinedagger.base.navigation

import com.dggorbachev.cinemaonlinedagger.base.common.Screen
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.ui.BookmarksFragment
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.ui.MovieDetailsFragment
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.MoviesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {
    fun MovieListScreen() = FragmentScreen { MoviesFragment.newInstance() }
    fun MovieDetailsScreen(movie: Movie, screen: Screen) =
        FragmentScreen { MovieDetailsFragment.newInstance(movie, screen) }

    fun BookmarksScreen() =
        FragmentScreen { BookmarksFragment.newInstance() }
}