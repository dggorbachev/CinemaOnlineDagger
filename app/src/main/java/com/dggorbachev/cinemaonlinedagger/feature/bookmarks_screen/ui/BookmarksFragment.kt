package com.dggorbachev.cinemaonlinedagger.feature.bookmarks_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dggorbachev.cinemaonlinedagger.MainActivity
import com.dggorbachev.cinemaonlinedagger.R
import com.dggorbachev.cinemaonlinedagger.base.common.Screen
import com.dggorbachev.cinemaonlinedagger.databinding.FragmentBookmarksFeedBinding
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.MoviesFragment
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.MoviesViewModel
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : Fragment(R.layout.fragment_bookmarks_feed) {

    private lateinit var binding: FragmentBookmarksFeedBinding
    private val viewModel: BookmarksViewModel by viewModels()
    private val filmsAdapter by lazy {
        MoviesAdapter(
            emptyList(),
            onMovieClick = { film ->
                viewModel.processUiEvent(UiEvent.OnMovieClick(film))
            }
        )
    }

    companion object {
        fun newInstance(): BookmarksFragment = BookmarksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksFeedBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeBarVisibility(View.VISIBLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmsRecyclerView = view.findViewById<RecyclerView>(R.id.rvFilms)
        filmsRecyclerView.adapter = filmsAdapter
        filmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
    }

    private fun render(viewState: ViewState) {
        filmsAdapter.updateFilms(viewState.moviesList)
    }
}