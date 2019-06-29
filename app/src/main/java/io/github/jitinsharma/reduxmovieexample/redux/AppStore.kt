package io.github.jitinsharma.reduxmovieexample.redux

import io.github.jitinsharma.reduxmovieexample.redux.middlewares.databaseMiddleWare
import io.github.jitinsharma.reduxmovieexample.redux.middlewares.movieMiddleWare
import io.github.jitinsharma.reduxmovieexample.redux.middlewares.networkMiddleWare
import io.github.jitinsharma.reduxmovieexample.redux.reducers.appReducer
import org.rekotlin.Store

val store = Store(
        reducer = ::appReducer,
        state = null,
        middleware = listOf(networkMiddleWare, databaseMiddleWare, movieMiddleWare)
)