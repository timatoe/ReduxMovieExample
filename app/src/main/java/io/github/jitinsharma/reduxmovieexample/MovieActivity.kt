package io.github.jitinsharma.reduxmovieexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import io.github.jitinsharma.reduxmovieexample.databinding.ActivityMovieBinding
import io.github.jitinsharma.reduxmovieexample.redux.actions.CheckForFavorites
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListCounterState
import io.github.jitinsharma.reduxmovieexample.redux.store
import org.rekotlin.StoreSubscriber

class MovieActivity : AppCompatActivity(), StoreSubscriber<FavoriteMovieListCounterState?> {

    private lateinit var navController: NavController
    private lateinit var favoriteMovieListBadgeDrawable: BadgeDrawable

    override fun newState(favoriteMovieListCounterState: FavoriteMovieListCounterState?) {
        favoriteMovieListCounterState?.apply {
            favoriteMovieListBadgeDrawable.isVisible = favoriteCount > 0
            favoriteMovieListBadgeDrawable.number = favoriteCount
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMovieBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_movie
        )

        navController = findNavController(R.id.nav_host_fragment)

        binding.bottomNavigation.setupWithNavController(navController)
        favoriteMovieListBadgeDrawable = binding.bottomNavigation.showBadge(R.id.favoriteMovieListFragment)

        store.dispatch(CheckForFavorites())
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select { appState ->
                appState.favoriteMovieListCounterState
            }
        }
    }

    override fun onStop() {
        store.unsubscribe(this)
        super.onStop()
    }

}