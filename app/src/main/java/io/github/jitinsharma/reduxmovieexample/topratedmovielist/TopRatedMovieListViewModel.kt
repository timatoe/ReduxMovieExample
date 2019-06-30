package io.github.jitinsharma.reduxmovieexample.topratedmovielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.states.TopRatedMovieListState
import io.github.jitinsharma.reduxmovieexample.redux.store
import org.rekotlin.StoreSubscriber

class TopRatedMovieListViewModel : ViewModel(), StoreSubscriber<TopRatedMovieListState> {

    private val _topRatedMoviesLiveData = MutableLiveData<List<MovieObject>>()

    init {
        store.subscribe(this) { appStateSubscription ->
            appStateSubscription.select { appState ->
                appState.topRatedMovieListState
            }.skipRepeats()
        }
    }

    override fun onCleared() {
        store.unsubscribe(this)
        super.onCleared()
    }

    override fun newState(state: TopRatedMovieListState) {
        _topRatedMoviesLiveData.value = state.movieObjects
    }

    val topRatedMoviesLiveData: LiveData<List<MovieObject>>
        get() = _topRatedMoviesLiveData

}