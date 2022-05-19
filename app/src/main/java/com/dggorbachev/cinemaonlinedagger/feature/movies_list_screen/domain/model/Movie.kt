package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val genreIds: List<Int>,
    val backdropPath: String? = "",
    val posterPath: String? = "",
    val voteAverage: Float,
    val isFavorite: Boolean = false
) : Parcelable