package io.github.jitinsharma.reduxmovieexample.redux.actions

import org.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */
sealed class FavoriteMovieListCounterActions {
    object CheckForFavorites : Action
    class SetInitialCount(val count: Int) : Action
    object Increment : Action
    object Decrement : Action
}