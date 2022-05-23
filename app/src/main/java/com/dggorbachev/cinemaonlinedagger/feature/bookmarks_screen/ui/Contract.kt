package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.ui

import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

data class ViewState(
    val moviesList: List<Movie>
)

sealed class UiEvent : Event {
    data class OnMovieClick(val movie: Movie) : UiEvent()
    data class OnBookmarkClick(val movie: Movie) : UiEvent()
    object RefreshScreen : UiEvent()
}

sealed class DataEvent : Event {
    object RefreshDataBase : DataEvent()
}