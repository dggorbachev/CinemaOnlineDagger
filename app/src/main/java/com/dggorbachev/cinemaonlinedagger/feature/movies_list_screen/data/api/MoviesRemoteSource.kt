package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api

import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.model.MoviesResponseModel
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteSource
@Inject constructor(private val movieApi: MovieApi) {
    suspend fun getMovies(): MoviesResponseModel {
        return movieApi.getMovies()
    }
}