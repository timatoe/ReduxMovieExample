package io.github.jitinsharma.reduxmovieexample.favoritemovielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.makeGone
import io.github.jitinsharma.reduxmovieexample.makeVisible
import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListState
import io.github.jitinsharma.reduxmovieexample.redux.store
import io.github.jitinsharma.reduxmovieexample.shared.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_favorite_movie_list.*
import org.rekotlin.StoreSubscriber

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieListFragment : Fragment(), StoreSubscriber<FavoriteMovieListState?> {
    private lateinit var movieListAdapter: MovieListAdapter

    override fun newState(stateMovie: FavoriteMovieListState?) {
        stateMovie?.apply {
            if (favorites.isEmpty()) {
                noFavoriteText.makeVisible()
                favoriteList.makeGone()
            } else {
                noFavoriteText.makeGone()
                favoriteList.makeVisible()
                initializeAdapter(favorites)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorite_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.dispatch(FavoriteMovieListActions.LoadFavoriteMovies)
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.favoriteMovieListState
            }
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        movieListAdapter = MovieListAdapter(movieObjects, true)
        favoriteList.layoutManager = GridLayoutManager(context, 2)
        favoriteList.adapter = movieListAdapter
    }
}