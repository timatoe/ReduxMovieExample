package io.github.jitinsharma.reduxmovieexample.redux.reducers

import io.github.jitinsharma.reduxmovieexample.redux.states.AppState
import org.rekotlin.Action

/**
 * Created by jsharma on 18/01/18.
 */

fun appReducer(action: Action, appState: AppState?): AppState =
        AppState(
                topRatedMovieListState = movieListReducer(action, appState?.topRatedMovieListState),
                favoriteMovieListCounterState = favoriteCounterReducer(action, appState?.favoriteMovieListCounterState),
                favoriteMovieListState = favoriteListReducer(action, appState?.favoriteMovieListState)
        )