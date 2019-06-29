package io.github.jitinsharma.reduxmovieexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.jitinsharma.reduxmovieexample.data.MovieObject

/**
 * Created by jsharma on 20/01/18.
 */

@Database(entities = [(MovieObject::class)], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
