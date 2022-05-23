package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.data.local.BookmarkEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,
    @ColumnInfo(name = "genreIds")
    val genreIds: List<Int>,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,
    @ColumnInfo(name = "posterPath")
    val posterPath: String? = "",
    @ColumnInfo(name = "voteAverage")
    val voteAverage: Float,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
) {
    companion object {
        const val TABLE_NAME = "BOOKMARKS_TABLE"
    }
}

/*
val id: Int,
val title: String,
val overview: String,
val releaseDate: String,
val genreIds: List<Int>,
val backdropPath: String? = "",
val posterPath: String? = "",
val voteAverage: Float,
val isFavorite: Boolean = false*/
