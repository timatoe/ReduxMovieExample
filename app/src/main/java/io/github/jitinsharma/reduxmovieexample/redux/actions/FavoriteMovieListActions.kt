package io.github.jitinsharma.reduxmovieexample.redux.actions

import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import org.rekotlin.Action

/**
 * Created by jsharma on 11/02/18.
 */

class LoadFavoriteMovies : Action

class SetFavoriteMovies(val favoriteMovies: List<MovieObject>) : Action