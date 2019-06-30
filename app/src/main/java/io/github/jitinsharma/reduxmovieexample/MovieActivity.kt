package io.github.jitinsharma.reduxmovieexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListCounterActions
import io.github.jitinsharma.reduxmovieexample.redux.store
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        navController = findNavController(R.id.nav_host_fragment)

        bottom_navigation.setupWithNavController(navController)
        val favoriteMovieListBadgeDrawable = bottom_navigation.showBadge(R.id.favoriteMovieListFragment)

        viewModel.favoriteMoviesCount.observe(this, Observer { favoriteMoviesCount ->
            favoriteMovieListBadgeDrawable.isVisible = favoriteMoviesCount > 0
            favoriteMovieListBadgeDrawable.number = favoriteMoviesCount
        })

        store.dispatch(FavoriteMovieListCounterActions.CheckForFavorites)
    }

}