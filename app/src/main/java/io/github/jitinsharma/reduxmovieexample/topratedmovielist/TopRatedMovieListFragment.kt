package io.github.jitinsharma.reduxmovieexample.topratedmovielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.github.jitinsharma.reduxmovieexample.R
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.actions.TopRatedMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.states.TopRatedMovieListState
import io.github.jitinsharma.reduxmovieexample.redux.store
import io.github.jitinsharma.reduxmovieexample.shared.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_movie_list.*
import org.rekotlin.StoreSubscriber

/**
 * A simple [Fragment] subclass.
 */
class TopRatedMovieListFragment : Fragment(), StoreSubscriber<TopRatedMovieListState?> {

    override fun newState(stateTopRated: TopRatedMovieListState?) {
        stateTopRated?.movieObjects?.let {
            initializeAdapter(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_top_rated_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.dispatch(TopRatedMovieListActions.LoadTopRatedMovies)
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        val movieListAdapter = MovieListAdapter(movieObjects)
        movieList.layoutManager = GridLayoutManager(context, 2)
        movieList.adapter = movieListAdapter
    }

    override fun onStart() {
        super.onStart()
        store.subscribe(this) {
            it.select {
                it.topRatedMovieListState
            }.skipRepeats()
        }
    }

    override fun onStop() {
        super.onStop()
        store.unsubscribe(this)
    }
}
