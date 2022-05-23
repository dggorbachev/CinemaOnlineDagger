package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data

import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local.BookmarkEntity
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie


fun Movie.toEntityModel() =
    BookmarkEntity(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        backdropPath = backdropPath,
        posterPath = posterPath,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )

fun BookmarkEntity.toDomainModel() =
    Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        genreIds = genreIds,
        backdropPath = backdropPath,
        posterPath = posterPath,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )