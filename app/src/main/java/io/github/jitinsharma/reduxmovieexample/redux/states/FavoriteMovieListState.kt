package io.github.jitinsharma.reduxmovieexample.redux.states

import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import org.rekotlin.StateType

/**
 * Created by jsharma on 11/02/18.
 */

data class FavoriteMovieListState(val favorites: List<MovieObject> = emptyList()) : StateType