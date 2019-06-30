package io.github.jitinsharma.reduxmovieexample.redux.middlewares

import io.github.jitinsharma.reduxmovieexample.MovieApplication
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListCounterActions
import io.github.jitinsharma.reduxmovieexample.redux.actions.TopRatedMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.states.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

/**
 * Created by jsharma on 15/01/18.
 */

internal val databaseMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is TopRatedMovieListActions.AddMovieToFavorites -> {
                    insertMovieAsync(action.movieObject, dispatch)
                }
                is TopRatedMovieListActions.RemoveMovieFromFavorites -> {
                    deleteMovieAsync(action.movieObject, dispatch)
                }
                is FavoriteMovieListCounterActions.CheckForFavorites -> {
                    getFavoriteCount(dispatch)
                }
                is FavoriteMovieListActions.LoadFavoriteMovies -> {
                    getFavoriteMovies(dispatch)
                }
            }
            next(action)
        }
    }
}

private fun insertMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        MovieApplication.movieDataBase?.movieDao()?.insert(movieObject)
        withContext(Dispatchers.Main) {
            dispatch.invoke(FavoriteMovieListCounterActions.Increment)
        }
    }
}

private fun deleteMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        MovieApplication.movieDataBase?.movieDao()?.delete(movieObject)
        withContext(Dispatchers.Main) {
            dispatch.invoke(FavoriteMovieListCounterActions.Decrement)
        }
    }
}

private fun getFavoriteCount(dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        val movies = MovieApplication.movieDataBase?.movieDao()?.getAll()
        withContext(Dispatchers.Main) {
            movies?.apply {
                dispatch.invoke(FavoriteMovieListCounterActions.SetInitialCount(size))
            }
        }
    }
}

private fun getFavoriteMovies(dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        val movies = MovieApplication.movieDataBase?.movieDao()?.getAll()
        withContext(Dispatchers.Main) {
            movies?.apply {
                dispatch(FavoriteMovieListActions.SetFavoriteMovies(this))
            }
        }
    }
}
