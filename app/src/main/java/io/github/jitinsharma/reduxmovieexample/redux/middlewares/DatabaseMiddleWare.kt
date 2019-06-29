package io.github.jitinsharma.reduxmovieexample.redux.middlewares

import io.github.jitinsharma.reduxmovieexample.MovieApplication
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.actions.*
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
                is AddMovieToFavorites -> {
                    insertMovieAsync(action.movieObject, dispatch)
                }
                is RemoveMovieFromFavorites -> {
                    deleteMovieAsync(action.movieObject, dispatch)
                }
                is CheckForFavorites -> {
                    getFavoriteCount(dispatch)
                }
                is LoadFavoriteMovies -> {
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
            dispatch.invoke(Increment())
        }
    }
}

private fun deleteMovieAsync(movieObject: MovieObject, dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        MovieApplication.movieDataBase?.movieDao()?.delete(movieObject)
        withContext(Dispatchers.Main) {
            dispatch.invoke(Decrement())
        }
    }
}

private fun getFavoriteCount(dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        val movies = MovieApplication.movieDataBase?.movieDao()?.getAll()
        withContext(Dispatchers.Main) {
            movies?.apply {
                dispatch.invoke(SetInitialCount(size))
            }
        }
    }
}

private fun getFavoriteMovies(dispatch: DispatchFunction) {
    CoroutineScope(Dispatchers.IO).launch {
        val movies = MovieApplication.movieDataBase?.movieDao()?.getAll()
        withContext(Dispatchers.Main) {
            movies?.apply {
                dispatch(SetFavoriteMovies(this))
            }
        }
    }
}
