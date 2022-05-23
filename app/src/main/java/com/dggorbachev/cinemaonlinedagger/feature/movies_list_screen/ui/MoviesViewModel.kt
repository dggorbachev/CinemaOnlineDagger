package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui

import com.dggorbachev.cinemaonlinedagger.base.BaseViewModel
import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.base.common.Screen
import com.dggorbachev.cinemaonlinedagger.base.navigation.Screens
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.MoviesInteractor
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject constructor(
    private val interactor: MoviesInteractor,
    private val router: Router
) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.OnLoadData)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            moviesList = emptyList(),
            isLoading = false
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.OnLoadData -> {
                processDataEvent(DataEvent.OnStartLoadData)
                interactor.getMovies().fold(
                    onError = {
                        processDataEvent(
                            DataEvent.ErrorRequest(it.message ?: "")
                        )
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessRequest(it))
                    }
                )
            }
            is DataEvent.OnStartLoadData -> {
                return previousState.copy(isLoading = true)
            }
            is DataEvent.SuccessRequest -> {
                return previousState.copy(moviesList = event.moviesList, isLoading = false)
            }
            is DataEvent.ErrorRequest -> {
                return previousState.copy(moviesList = emptyList(), isLoading = false)
            }
            is UiEvent.OnMovieClick -> {
                val screen =
                    Screens.MovieDetailsScreen(movie = event.movie, Screen.MOVIES_FEED)
                router.navigateTo(screen)
            }
        }
        return null
    }

}