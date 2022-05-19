package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data

import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.data.api.model.MovieModel
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

fun MovieModel.toDomain() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    genreIds = genreIds,
    backdropPath = backdropPath,
    posterPath = posterPath,
    voteAverage = voteAverage
)

