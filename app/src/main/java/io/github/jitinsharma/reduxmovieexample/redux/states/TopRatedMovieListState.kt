package io.github.jitinsharma.reduxmovieexample.redux.states

import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import org.rekotlin.StateType

/**
 * Created by jsharma on 15/01/18.
 */

data class TopRatedMovieListState(var movieObjects: List<MovieObject> = emptyList()) : StateType