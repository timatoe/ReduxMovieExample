package io.github.jitinsharma.reduxmovieexample.redux.states

import org.rekotlin.StateType

/**
 * Created by jsharma on 15/01/18.
 */

data class AppState(
        var topRatedMovieListState: TopRatedMovieListState? = null,
        var favoriteMovieListCounterState: FavoriteMovieListCounterState? = null,
        var favoriteMovieListState: FavoriteMovieListState? = null
) : StateType