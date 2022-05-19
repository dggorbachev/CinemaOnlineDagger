package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api

import com.dggorbachev.cinemaonlinedagger.base.common.Constants.TMDB_API_KEY
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.model.TrailersResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrailersApi {
    @GET("/3/movie/{movie_id}/videos")
    suspend fun getVideosList(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = TMDB_API_KEY,
        @Query("language") language: String = "en-US"
    ): TrailersResponseModel
}