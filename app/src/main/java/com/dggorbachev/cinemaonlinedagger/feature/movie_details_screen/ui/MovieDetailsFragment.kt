package com.dggorbachev.cinemaonlinedagger.feature.movie_details_screen.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.dggorbachev.cinemaonlinedagger.MainActivity
import com.dggorbachev.cinemaonlinedagger.R
import com.dggorbachev.cinemaonlinedagger.base.LambdaFactory
import com.dggorbachev.cinemaonlinedagger.base.common.Constants.IMG_URL
import com.dggorbachev.cinemaonlinedagger.base.common.Screen
import com.dggorbachev.cinemaonlinedagger.base.navigation.BackButtonListener
import com.dggorbachev.cinemaonlinedagger.base.utils.setThrottledClickListener
import com.dggorbachev.cinemaonlinedagger.databinding.FragmentMovieDetailsBinding
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.domain.model.Movie
import com.dggorbachev.cinemaonlinedagger.feature.player_screen.ui.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details), BackButtonListener {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val EXTRA_MOVIE = "extra_movie"
        private const val PREVIOUS_SCREEN = "previous_screen"

        fun newInstance(
            movie: Movie,
            screen: Screen
        ): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                arguments = bundleOf(
                    EXTRA_MOVIE to movie,
                    PREVIOUS_SCREEN to screen
                )
            }
        }
    }

    private val movie: Movie
        get() = requireArguments().getParcelable(EXTRA_MOVIE)!!
    private val previousScreen: Screen
        get() = (requireArguments().getSerializable(PREVIOUS_SCREEN)!! as Screen)

    @Inject
    lateinit var factory: MovieDetailsViewModel.Factory
    private val viewModel: MovieDetailsViewModel by viewModels {
        LambdaFactory(this) { stateHandle ->
            factory.build(stateHandle, movie = movie, previousScreen = previousScreen)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeBarVisibility(View.GONE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        viewModel.openTrailer.observe(viewLifecycleOwner, Observer(::openTrailer))
    }

    private fun render(state: ViewState) {
        binding.tvFilmTitle.text = movie.title
        binding.tvDescInfo.text = movie.overview

        Glide.with(binding.root)
            .load(IMG_URL + movie.posterPath)
            .centerCrop()
            .into(binding.ivPoster)

        binding.btnPlay.setThrottledClickListener {
            viewModel.processUiEvent(UiEvent.OnWatchClick(state.videoKey))
        }
        binding.cbSave.setThrottledClickListener {
            viewModel.processUiEvent(UiEvent.OnBookmarkClick)
        }
        binding.cbSave.apply {
            if (state.movie.isFavorite) {
                binding.cbSave.setButtonDrawable(R.drawable.save)
            } else {
                binding.cbSave.setButtonDrawable(R.drawable.save_border)
            }
        }
    }


    private fun openTrailer(event: UiEvent.OnWatchClick) {
        val intent = Intent(context, PlayerActivity::class.java)
        val b = Bundle()
        b.putString("videoKey", event.videoKey) //Your id

        intent.putExtras(b) //Put your id to your next Intent
        startActivity(intent);
    }

    override fun onBackPressed(): Boolean {
        viewModel.processUiEvent(UiEvent.OnBackPressed)
        return true
    }
}