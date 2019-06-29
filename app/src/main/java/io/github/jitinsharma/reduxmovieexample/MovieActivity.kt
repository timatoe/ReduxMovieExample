package io.github.jitinsharma.reduxmovieexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.roughike.bottombar.BottomBarTab
import com.roughike.bottombar.OnTabSelectListener
import io.github.jitinsharma.reduxmovieexample.favoritemovielist.FavoriteMovieListFragment
import io.github.jitinsharma.reduxmovieexample.redux.actions.CheckForFavorites
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListCounterState
import io.github.jitinsharma.reduxmovieexample.redux.store
import io.github.jitinsharma.reduxmovieexample.topratedmovielist.TopRatedMovieListFragment
import kotlinx.android.synthetic.main.activity_movie.*
import org.rekotlin.StoreSubscriber

@SuppressLint("PrivateResource")
class MovieActivity : AppCompatActivity(), OnTabSelectListener, StoreSubscriber<FavoriteMovieListCounterState?> {
    private lateinit var favoriteTab: BottomBarTab

    override fun newState(stateMovieList: FavoriteMovieListCounterState?) {
        stateMovieList?.apply {
            when (favoriteCount) {
                0 -> favoriteTab.removeBadge()
                else -> favoriteTab.setBadgeCount(favoriteCount)
            }
        }
    }

    override fun onTabSelected(tabId: Int) {
        when (tabId) {
            R.id.tab_list -> {
                showFragment(TopRatedMovieListFragment())
            }
            R.id.tab_favorite -> {
                showFragment(FavoriteMovieListFragment())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        bottomBar.setOnTabSelectListener(this)
        favoriteTab = bottomBar.getTabWithId(R.id.tab_favorite)
        showFragment(TopRatedMovieListFragment())
        store.dispatch(CheckForFavorites())
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.favoriteMovieListCounterState
            }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    private fun showFragment(fragment: Fragment) {
        transact {
            replace(R.id.container, fragment)
            setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
        }
    }
}

inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}