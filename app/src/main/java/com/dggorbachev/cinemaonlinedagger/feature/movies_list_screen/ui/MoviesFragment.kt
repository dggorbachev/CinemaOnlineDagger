package com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.dggorbachev.cinemaonlinedagger.R
import com.dggorbachev.cinemaonlinedagger.databinding.FragmentMoviesFeedBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dggorbachev.cinemaonlinedagger.MainActivity
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.adapter.MoviesAdapter

@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies_feed) {

    private var _binding: FragmentMoviesFeedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private val moviesAdapter by lazy {
        MoviesAdapter(
            emptyList()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesFeedBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeBarVisibility(View.VISIBLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmsRecyclerView = view.findViewById<RecyclerView>(R.id.rvFilms)
        filmsRecyclerView.adapter = moviesAdapter
        filmsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.viewState.observe(
            viewLifecycleOwner, Observer(::render)
        )
    }

    private fun render(viewState: ViewState) {
        moviesAdapter.updateFilms(viewState.moviesList)
        binding.progressBar.isGone = !viewState.isLoading
    }
}