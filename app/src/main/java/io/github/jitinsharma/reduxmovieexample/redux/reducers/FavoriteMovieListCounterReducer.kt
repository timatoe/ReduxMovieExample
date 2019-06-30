package io.github.jitinsharma.reduxmovieexample.redux.reducers

import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListCounterActions
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListCounterState
import org.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */

fun favoriteCounterReducer(action: Action, favoriteMovieListCounterState: FavoriteMovieListCounterState?)
        : FavoriteMovieListCounterState {
    var state = favoriteMovieListCounterState ?: FavoriteMovieListCounterState()
    when (action) {
        is FavoriteMovieListCounterActions.SetInitialCount -> {
            state = state.copy(favoriteCount = action.count)
        }
        is FavoriteMovieListCounterActions.Increment -> {
            state = state.copy(favoriteCount = state.favoriteCount + 1)
        }
        is FavoriteMovieListCounterActions.Decrement -> {
            state = state.copy(favoriteCount = state.favoriteCount - 1)
        }
    }
    return state
}