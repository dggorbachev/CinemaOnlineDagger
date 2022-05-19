package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class TrailersResponseModel(
    @SerializedName("results")
    val results: List<TrailerModel>
)