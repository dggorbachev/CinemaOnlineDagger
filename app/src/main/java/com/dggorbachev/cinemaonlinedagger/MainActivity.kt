package com.dggorbachev.cinemaonlinedagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dggorbachev.cinemaonlinedagger.feature.movies_list_screen.ui.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MoviesFragment())
            .commit()
    }
}