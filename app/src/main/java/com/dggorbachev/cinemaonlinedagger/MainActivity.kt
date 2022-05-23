package com.dggorbachev.cinemaonlinedagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dggorbachev.cinemaonlinedagger.base.navigation.BackButtonListener
import com.dggorbachev.cinemaonlinedagger.base.navigation.Screens
import com.dggorbachev.cinemaonlinedagger.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = object : AppNavigator(this, R.id.flMain) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigatorHolder.setNavigator(navigator)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigationMoviesFeed -> {
                    val screen = Screens.MovieListScreen()
                    router.newRootScreen(screen)
                }
                R.id.navigationBookmarksFeed -> {
                    val screen = Screens.BookmarksScreen()
                    router.newRootScreen(screen)
                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.navigationMoviesFeed
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.flMain)
        if (fragment != null && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
        }
    }

    fun changeBarVisibility(visibility: Int) {
        binding.bottomNavigationView.visibility = visibility
    }
}