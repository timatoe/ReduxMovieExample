package io.github.jitinsharma.reduxmovieexample.redux.actions

import org.rekotlin.Action

/**
 * Created by jsharma on 30/01/18.
 */

class CheckForFavorites : Action

class SetInitialCount(val count: Int) : Action

class Increment : Action

class Decrement : Action