package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api

import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.model.TrailersResponseModel
import javax.inject.Inject

class TrailersRemoteSource
@Inject constructor(private val trailersApi: TrailersApi) {
    suspend fun getTrailers(movieId: Int): TrailersResponseModel {
        return trailersApi.getVideosList(movieId = movieId)
    }
}