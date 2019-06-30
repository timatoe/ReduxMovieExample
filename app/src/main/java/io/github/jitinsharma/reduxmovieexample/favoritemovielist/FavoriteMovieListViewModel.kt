package io.github.jitinsharma.reduxmovieexample.favoritemovielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.states.FavoriteMovieListState
import io.github.jitinsharma.reduxmovieexample.redux.store
import org.rekotlin.StoreSubscriber

class FavoriteMovieListViewModel : ViewModel(), StoreSubscriber<FavoriteMovieListState> {

    private val _favoriteMoviesLiveData = MutableLiveData<List<MovieObject>>()

    init {
        store.subscribe(this) { appStateSubscription ->
            appStateSubscription.select { appState ->
                appState.favoriteMovieListState
            }
        }
    }

    override fun onCleared() {
        store.unsubscribe(this)
        super.onCleared()
    }

    override fun newState(state: FavoriteMovieListState) {
        _favoriteMoviesLiveData.value = state.favorites
    }

    val favoriteMoviesLiveData: LiveData<List<MovieObject>>
        get() = _favoriteMoviesLiveData

}