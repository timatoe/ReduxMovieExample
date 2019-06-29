package io.github.jitinsharma.reduxmovieexample.shared

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.github.jitinsharma.reduxmovieexample.*
import io.github.jitinsharma.reduxmovieexample.data.MovieObject
import io.github.jitinsharma.reduxmovieexample.redux.actions.AddMovieToFavorites
import io.github.jitinsharma.reduxmovieexample.redux.actions.RemoveMovieFromFavorites
import io.github.jitinsharma.reduxmovieexample.redux.store

/**
 * Created by jsharma on 15/01/18.
 */
class MovieListAdapter(private val movieObjects: List<MovieObject>,
                       private var fromFavorites: Boolean = false) :
        RecyclerView.Adapter<MovieListAdapter.MovieItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder =
            MovieItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie,
                    parent, false))

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        val current = movieObjects[position]
        holder.apply {
            movieRating.text = current.voteAverage.toString()
            movieItemImage.loadImage(getImageUrl(current.posterPath.toString()))
            if (fromFavorites) {
                addToFavorites.makeInvisible()
            } else {
                addToFavorites.makeVisible()
                if (current.isFavorite) {
                    addToFavorites.setImageDrawable(
                            ContextCompat.getDrawable(addToFavorites.context,
                                    R.drawable.ic_favorite_red_24dp)
                    )
                } else {
                    addToFavorites.setImageDrawable(
                            ContextCompat.getDrawable(addToFavorites.context,
                                    R.drawable.ic_favorite_border_black_24dp)
                    )
                }
            }
        }
    }

    private fun getImageUrl(posterPath: String): String = imageUrlPrefix + posterPath

    override fun getItemCount(): Int = movieObjects.size

    inner class MovieItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var movieRating: TextView
        var addToFavorites: ImageView
        var movieItemImage: ImageView

        init {
            itemView.apply {
                movieRating = findViewById(R.id.movieRating)
                addToFavorites = findViewById(R.id.addToFavorites)
                movieItemImage = findViewById(R.id.movieItemImage)
                addToFavorites.setOnClickListener {
                    movieObjects[adapterPosition].apply {
                        handleFavoriteClick()
                        onBindViewHolder(this@MovieItemHolder, adapterPosition)
                    }
                }
            }
        }

        private fun MovieObject.handleFavoriteClick() {
            if (!isFavorite) {
                isFavorite = true
                store.dispatch(AddMovieToFavorites(this))
            } else {
                isFavorite = false
                store.dispatch(RemoveMovieFromFavorites(this))
            }
        }
    }
}