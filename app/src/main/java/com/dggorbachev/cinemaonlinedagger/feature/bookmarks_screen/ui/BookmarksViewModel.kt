package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.ui

import com.dggorbachev.cinemaonlinedagger.base.BaseViewModel
import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.base.common.Screen
import com.dggorbachev.cinemaonlinedagger.base.navigation.Screens
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.domain.BookmarksInteractor
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel
@Inject constructor(
    private val bookmarksInteractor: BookmarksInteractor,
    private val router: Router
) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.RefreshDataBase)
    }

    override fun initialViewState(): ViewState {
        return ViewState(moviesList = emptyList())
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is DataEvent.RefreshDataBase -> {
                val newMoviesList = bookmarksInteractor.read()
                return previousState.copy(moviesList = newMoviesList)
            }
            is UiEvent.OnBookmarkClick -> {
                bookmarksInteractor.delete(event.movie)
            }
            is UiEvent.RefreshScreen -> {
                processDataEvent(DataEvent.RefreshDataBase)
            }
            is UiEvent.OnMovieClick -> {
                val screen =
                    Screens.MovieDetailsScreen(movie = event.movie, Screen.BOOKMARKS_FEED)
                router.navigateTo(screen)
            }
        }
        return null
    }
}