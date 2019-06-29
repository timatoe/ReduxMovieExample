package io.github.jitinsharma.reduxmovieexample.redux.reducers

import io.github.jitinsharma.reduxmovieexample.redux.actions.Decrement
import io.github.jitinsharma.reduxmovieexample.redux.actions.Increment
import io.github.jitinsharma.reduxmovieexample.redux.actions.SetInitialCount
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListCounterState
import org.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */

fun favoriteCounterReducer(action: Action, favoriteMovieListCounterState: FavoriteMovieListCounterState?)
        : FavoriteMovieListCounterState {
    var state = favoriteMovieListCounterState ?: FavoriteMovieListCounterState()
    when (action) {
        is SetInitialCount -> {
            state = state.copy(favoriteCount = action.count)
        }
        is Increment -> {
            state = state.copy(favoriteCount = state.favoriteCount + 1)
        }
        is Decrement -> {
            state = state.copy(favoriteCount = state.favoriteCount - 1)
        }
    }
    return state
}