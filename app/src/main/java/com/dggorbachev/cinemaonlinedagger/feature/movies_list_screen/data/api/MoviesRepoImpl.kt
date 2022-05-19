package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api

import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.toDomain
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import javax.inject.Inject

class MoviesRepoImpl
@Inject constructor(private val source: MoviesRemoteSource) : MoviesRepo {
    override suspend fun getMovies(): List<Movie> {
        return source.getMovies().results.map {
            it.toDomain()
        }
    }
}