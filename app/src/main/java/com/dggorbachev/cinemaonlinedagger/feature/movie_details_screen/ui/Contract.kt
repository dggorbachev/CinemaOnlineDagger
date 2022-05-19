package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.ui

import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.model.Trailer
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie

data class ViewState(
    val videoKey: String,
    val movie: Movie
)

sealed class DataEvent : Event {
    data class OnLoadData(val movieId: Int) : DataEvent()
    data class SuccessVideosRequest(val trailersList: List<Trailer>) : DataEvent()
    object ErrorVideosRequest : DataEvent()
}

sealed class UiEvent : Event {
    data class OnWatchClick(val videoKey: String) : UiEvent()
    object OnBookmarkClick : UiEvent()
    object OnBackPressed : UiEvent()
}