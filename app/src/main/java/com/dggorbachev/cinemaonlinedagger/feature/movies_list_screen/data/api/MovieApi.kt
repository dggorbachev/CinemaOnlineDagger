package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api

import androidx.annotation.IntRange
import com.dggorbachev.cinemaonlinedagger.base.common.Constants.END_POINT_NOW_PLAYING
import com.dggorbachev.cinemaonlinedagger.base.common.Constants.TMDB_API_KEY
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.model.MoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET(END_POINT_NOW_PLAYING)
    suspend fun getMovies(
        @Query("api_key") apiKey: String = TMDB_API_KEY,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("language") language: String = "en-US"
    ): MoviesResponseModel
}