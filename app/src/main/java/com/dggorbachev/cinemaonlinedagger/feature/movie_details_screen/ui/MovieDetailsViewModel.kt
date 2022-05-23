package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.ui

import androidx.lifecycle.SavedStateHandle
import com.dggorbachev.cinemaonlinedagger.base.BaseViewModel
import com.dggorbachev.cinemaonlinedagger.base.Event
import com.dggorbachev.cinemaonlinedagger.base.common.Screen
import com.dggorbachev.cinemaonlinedagger.base.navigation.Screens
import com.dggorbachev.cinemaonlinedagger.base.utils.SingleLiveEvent
import com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.domain.BookmarksInteractor
import com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.domain.TrailersInteractor
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

class MovieDetailsViewModel
@AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    @Assisted private val movie: Movie,
    @Assisted private val previousScreen: Screen,
    private val trailersInteractor: TrailersInteractor,
    private val bookmarksInteractor: BookmarksInteractor,
    private val router: Router
) : BaseViewModel<ViewState>() {

    val openTrailer = SingleLiveEvent<UiEvent.OnWatchClick>()

    @AssistedFactory
    interface Factory {
        fun build(
            stateHandle: SavedStateHandle,
            movie: Movie,
            previousScreen: Screen
        ): MovieDetailsViewModel
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
                trailersInteractor.getTrailers(event.movieId).fold(
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
                openTrailer.value = event
            }
            is UiEvent.OnBookmarkClick -> {
                val favorite = !movie.isFavorite

                if (favorite)
                    bookmarksInteractor.create(movie.copy(isFavorite = favorite))
                else
                    bookmarksInteractor.delete(movie.copy(isFavorite = favorite))

                return previousState.copy(
                    movie = movie.copy(isFavorite = favorite)
                )
            }
            is UiEvent.OnBackPressed -> {
                if (previousScreen == Screen.MOVIES_FEED)
                    router.navigateTo(Screens.MovieListScreen())
                else
                    router.navigateTo(Screens.BookmarksScreen())
            }
        }
        return null
    }

}