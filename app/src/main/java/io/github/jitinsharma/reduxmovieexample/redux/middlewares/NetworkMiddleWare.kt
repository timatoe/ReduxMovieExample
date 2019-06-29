package io.github.jitinsharma.reduxmovieexample.redux.middlewares

import io.github.jitinsharma.reduxmovieexample.API_KEY
import io.github.jitinsharma.reduxmovieexample.data.remote.MovieApiClient
import io.github.jitinsharma.reduxmovieexample.data.remote.MovieApiInterface
import io.github.jitinsharma.reduxmovieexample.redux.actions.InitializeMovieList
import io.github.jitinsharma.reduxmovieexample.redux.actions.LoadTopRatedMovies
import io.github.jitinsharma.reduxmovieexample.redux.states.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

/**
 * Created by jsharma on 18/01/18.
 */

internal val networkMiddleWare: Middleware<AppState> = { dispatch, _ ->
    { next ->
        { action ->
            when (action) {
                is LoadTopRatedMovies -> {
                    callTopRatedMovies(dispatch)
                }
            }
            next(action)
        }
    }
}

private fun callTopRatedMovies(dispatch: DispatchFunction) {
    val apiService = MovieApiClient.client?.create(MovieApiInterface::class.java)
    CoroutineScope(Dispatchers.IO).launch {
        val movieResponse = apiService?.discoverMovies(API_KEY)
        val movies = movieResponse?.results ?: emptyList()
        withContext(Dispatchers.Main) {
            dispatch(InitializeMovieList(movies))
        }
    }
}