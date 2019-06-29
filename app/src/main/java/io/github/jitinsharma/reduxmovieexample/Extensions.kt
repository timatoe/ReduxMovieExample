package io.github.jitinsharma.reduxmovieexample

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by jsharma on 15/01/18.
 */

fun ImageView.loadImage(url: String) {
    Picasso.with(context)
            .load(url)
            .into(this)
}

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}
