package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api

import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.toDomain
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.model.Trailer
import javax.inject.Inject

class TrailersRepoImpl
@Inject constructor(val source: TrailersRemoteSource) : TrailersRepo {
    override suspend fun getTrailers(movieId: Int): List<Trailer> {
        return source.getTrailers(movieId = movieId).results.map {
            it.toDomain()
        }
    }
}