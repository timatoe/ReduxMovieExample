package io.github.jitinsharma.reduxmovieexample.redux.actions

import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import org.rekotlin.Action

/**
 * Created by jsharma on 15/01/18.
 */
sealed class TopRatedMovieListActions {
    class InitializeMovieList(val movieObjects: List<MovieObject>) : Action
    class DisplayMovies(val movieObjects: List<MovieObject>) : Action
    object LoadTopRatedMovies : Action
    class AddMovieToFavorites(val movieObject: MovieObject) : Action
    class RemoveMovieFromFavorites(val movieObject: MovieObject) : Action
}