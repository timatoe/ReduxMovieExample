package io.github.jitinsharma.reduxmovieexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListCounterState
import io.github.jitinsharma.reduxmovieexample.redux.store
import org.rekotlin.StoreSubscriber

class MovieViewModel : ViewModel(), StoreSubscriber<FavoriteMovieListCounterState> {

    private val _favoriteMoviesCount = MutableLiveData<Int>()

    init {
        store.subscribe(this) { appStateSubscription ->
            appStateSubscription.select { appState ->
                appState.favoriteMovieListCounterState
            }
        }
    }

    override fun onCleared() {
        store.unsubscribe(this)
        super.onCleared()
    }

    override fun newState(state: FavoriteMovieListCounterState) {
        _favoriteMoviesCount.value = state.favoriteCount
    }

    val favoriteMoviesCount: LiveData<Int>
        get() = _favoriteMoviesCount

}