package io.github.jitinsharma.reduxmovieexample.topratedmovielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.databinding.FragmentTopRatedMovieListBinding
import io.github.jitinsharma.reduxmovieexample.redux.actions.TopRatedMovieListActions
import io.github.jitinsharma.reduxmovieexample.redux.store
import io.github.jitinsharma.reduxmovieexample.shared.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_top_rated_movie_list.*

/**
 * A simple [Fragment] subclass.
 */
class TopRatedMovieListFragment : Fragment() {

    private val viewModel: TopRatedMovieListViewModel by lazy {
        ViewModelProviders.of(this).get(TopRatedMovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentTopRatedMovieListBinding.inflate(inflater, container, false)
        viewModel.topRatedMoviesLiveData.observe(this, Observer { topRatedMovies ->
            initializeAdapter(topRatedMovies)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        store.dispatch(TopRatedMovieListActions.LoadTopRatedMovies)
    }

    private fun initializeAdapter(movieObjects: List<MovieObject>) {
        val movieListAdapter = MovieListAdapter(movieObjects)
        movieList.layoutManager = GridLayoutManager(context, 2)
        movieList.adapter = movieListAdapter
    }

}
