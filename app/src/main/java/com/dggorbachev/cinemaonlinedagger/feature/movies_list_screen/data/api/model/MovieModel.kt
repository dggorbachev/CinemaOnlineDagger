package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @SerializedName("vote_average")
    val voteAverage: Float
)