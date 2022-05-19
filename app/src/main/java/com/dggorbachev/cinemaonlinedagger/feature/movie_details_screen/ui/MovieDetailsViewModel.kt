package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.ui

import androidx.lifecycle.SavedStateHandle
import com.dggorbachev.cinemaonlinedagger.base.BaseViewModel
import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.base.utils.SingleLiveEvent
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.TrailersInteractor
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

class MovieDetailsViewModel
@AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    @Assisted private val movie: Movie,
    private val interactor: TrailersInteractor
) : BaseViewModel<ViewState>() {

    val openFilm = SingleLiveEvent<UiEvent.OnWatchClick>()

    @AssistedFactory
    interface Factory {
        fun build(stateHandle: SavedStateHandle, screenId: Movie): MovieDetailsViewModel
    }

    init {
        processDataEvent(DataEvent.OnLoadData(movie.id))
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            videoKey = "",
            movie = movie
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.OnLoadData -> {
                interactor.getTrailers(event.movieId).fold(
                    onSuccess = {
                        if (it.isEmpty())
                            processDataEvent(DataEvent.ErrorVideosRequest)
                        else
                            processDataEvent(DataEvent.SuccessVideosRequest(it))
                    },
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorVideosRequest
                        )
                    }
                )
            }
            is DataEvent.SuccessVideosRequest -> {
                return previousState.copy(
                    videoKey = event.trailersList[0].key
                )
            }
            is DataEvent.ErrorVideosRequest -> {
                return previousState.copy(
                    videoKey = "dQw4w9WgXcQ"
                )
            }
            is UiEvent.OnWatchClick -> {
                openFilm.value = event
            }
        }
        return null
    }

}