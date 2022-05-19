package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api

import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

interface MoviesRepo {
    suspend fun getMovies(): List<Movie>
}