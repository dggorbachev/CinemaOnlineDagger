package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer(
    val key: String,
    val type: String
) : Parcelable