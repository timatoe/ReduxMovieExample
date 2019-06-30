package io.github.jitinsharma.reduxmovieexample.redux.middlewares

import io.github.jitinsharma.reduxmovieexample.MovieApplication
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.actions.TopRatedMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.states.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

/**
 * Created by jsharma on 30/01/18.
 */

internal val movieMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is TopRatedMovieListActions.InitializeMovieList -> {
                    updateMoviesWithFavorites(action.movieObjects, dispatch)
                }
            }
            next(action)
        }
    }
}

private fun updateMoviesWithFavorites(movieObjects: List<MovieObject>, dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        var favoriteMap: Map<Int, Long?> = mapOf()
        val movies = MovieApplication.movieDataBase?.movieDao()?.getAll()
        withContext(Dispatchers.Main) {
            movies?.let {
                favoriteMap = it.mapIndexed { index, movieObject -> index to movieObject.id }.toMap()
            }
            movieObjects.forEach {
                if (favoriteMap.containsValue(it.id)) {
                    it.isFavorite = true
                }
            }
            dispatch(TopRatedMovieListActions.DisplayMovies(movieObjects))
        }
    }
}