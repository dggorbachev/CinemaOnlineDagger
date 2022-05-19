package com.dggorbachev.cinemaonlinedagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dggorbachev.cinemaonlinedagger.databinding.ActivityMainBinding
import com.dggorbachev.cinemaonlinedagger.databinding.FragmentMoviesFeedBinding
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun changeBarVisibility(visibility: Int) {
        binding.bottomNavigationView.visibility = visibility
    }
}