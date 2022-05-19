package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui

import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

data class ViewState(
    val moviesList: List<Movie>
)

sealed class DataEvent : Event {
    object OnLoadData : DataEvent()
    data class SuccessRequest(val moviesList: List<Movie>) : DataEvent()
    data class ErrorRequest(val message: String) : DataEvent()
}