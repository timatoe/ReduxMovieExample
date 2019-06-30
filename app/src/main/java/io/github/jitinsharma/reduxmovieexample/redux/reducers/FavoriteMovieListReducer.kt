package io.github.jitinsharma.reduxmovieexample.redux.reducers

import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListState
import org.rekotlin.Action

/**
 * Created by jsharma on 11/02/18.
 */

fun favoriteListReducer(action: Action, favoriteMovieListState: FavoriteMovieListState?): FavoriteMovieListState {
    var state = favoriteMovieListState ?: FavoriteMovieListState()
    when (action) {
        is FavoriteMovieListActions.SetFavoriteMovies -> {
            state = state.copy(favorites = action.favoriteMovies)
        }
    }
    return state
}