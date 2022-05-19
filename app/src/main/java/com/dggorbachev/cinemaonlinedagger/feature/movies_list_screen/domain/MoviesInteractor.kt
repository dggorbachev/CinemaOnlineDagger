package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain

import com.dggorbachev.cinemaonlinedagger.base.attempt
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.MoviesRepo
import javax.inject.Inject

class MoviesInteractor
@Inject constructor(val moviesRepo: MoviesRepo) {
    suspend fun getMovies() = attempt {
        moviesRepo.getMovies()
    }
}