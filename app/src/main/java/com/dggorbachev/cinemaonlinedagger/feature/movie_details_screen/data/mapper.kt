package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data

import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.data.api.model.TrailerModel
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.model.Trailer

fun TrailerModel.toDomain(): Trailer = Trailer(
    key = key,
    type = type
)