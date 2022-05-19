package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui

import com.dggorbachev.cinemaonlinedagger.base.BaseViewModel
import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.MoviesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject constructor(private val interactor: MoviesInteractor) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.OnLoadData)
    }

    override fun initialViewState(): ViewState {
        return ViewState(moviesList = emptyList())
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.OnLoadData -> {
                interactor.getMovies()
                    .fold(onError = {
                        processDataEvent(
                            DataEvent.ErrorRequest(it.message ?: "")
                        )
                    },
                        onSuccess = {
                            processDataEvent(DataEvent.SuccessRequest(it))
                        }
                    )
            }
            is DataEvent.SuccessRequest -> {
                return previousState.copy(moviesList = event.moviesList)
            }
            is DataEvent.ErrorRequest -> {
                return previousState.copy(moviesList = emptyList())
            }
        }
        return null
    }

}