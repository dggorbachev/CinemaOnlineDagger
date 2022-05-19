package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api

import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.model.Trailer

interface TrailersRepo {
    suspend fun getTrailers(movieId: Int): List<Trailer>
}