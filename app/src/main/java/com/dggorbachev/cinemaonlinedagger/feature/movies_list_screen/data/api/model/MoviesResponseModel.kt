package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseModel(
    @SerializedName("results")
    val results: List<MovieModel>
)