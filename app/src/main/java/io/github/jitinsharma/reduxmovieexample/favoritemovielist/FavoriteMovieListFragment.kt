package io.github.jitinsharma.reduxmovieexample.favoritemovielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.databinding.FragmentFavoriteMovieListBinding
import io.github.jitinsharma.reduxmovieexample.makeGone
import io.github.jitinsharma.reduxmovieexample.makeVisible
import io.github.jitinsharma.reduxmovieexample.redux.actions.FavoriteMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.store
import io.github.jitinsharma.reduxmovieexample.shared.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_favorite_movie_list.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieListFragment : Fragment() {

    private val viewModel: FavoriteMovieListViewModel by lazy {
        ViewModelProviders.of(this).get(FavoriteMovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentFavoriteMovieListBinding.inflate(inflater, container, false)
        viewModel.favoriteMoviesLiveData.observe(this, Observer { favoriteMovies ->
            if (favoriteMovies.isEmpty()) {
                noFavoriteText.makeVisible()
                favoriteList.makeGone()
            } else {
                noFavoriteText.makeGone()
                favoriteList.makeVisible()
                initializeAdapter(favoriteMovies)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.dispatch(FavoriteMovieListActions.LoadFavoriteMovies)
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        val movieListAdapter = MovieListAdapter(movieObjects, true)
        favoriteList.layoutManager = GridLayoutManager(context, 2)
        favoriteList.adapter = movieListAdapter
    }
}