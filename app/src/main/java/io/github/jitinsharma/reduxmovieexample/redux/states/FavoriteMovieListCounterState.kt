package io.github.jitinsharma.reduxmovieexample.redux.states

import org.rekotlin.StateType

/**
 * Created by jsharma on 30/01/18.
 */

data class FavoriteMovieListCounterState(val favoriteCount: Int = 0) : StateType