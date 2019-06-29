package io.github.jitinsharma.reduxmovieexample.redux.reducers

import io.github.jitinsharma.reduxmovieexample.redux.actions.DisplayMovies
import io.github.jitinsharma.reduxmovieexample.redux.states.TopRatedMovieListState
import org.rekotlin.Action

/**
 * Created by jsharma on 15/01/18.
 */

fun movieListReducer(action: Action, topRatedMovieListState: TopRatedMovieListState?): TopRatedMovieListState {
    var state = topRatedMovieListState ?: TopRatedMovieListState()
    when (action) {
        is DisplayMovies -> {
            state = state.copy(movieObjects = action.movieObjects)
        }
    }
    return state
}