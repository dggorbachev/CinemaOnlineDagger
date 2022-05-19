package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain

import com.dggorbachev.cinemaonlinedagger.base.attempt
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.TrailersRepo

class TrailersInteractor(private val repo: TrailersRepo) {
    suspend fun getTrailers(movieId: Int) = attempt {
        repo.getTrailers(movieId = movieId).filter {
            it.type == "Trailer"
        }
    }
}