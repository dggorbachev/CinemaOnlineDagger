package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class TrailerModel(
    @SerializedName("key")
    val key: String,
    @SerializedName("type")
    val type: String
)