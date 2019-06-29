package io.github.jitinsharma.reduxmovieexample.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.jitinsharma.reduxmovieexample.data.MovieObject

/**
 * Created by jsharma on 20/01/18.
 */

@Dao
interface MovieDao {

    @Query("SELECT * from movies")
    fun getAll(): List<MovieObject>

    @Insert
    fun insertAll(vararg movieObject: MovieObject)

    @Insert
    fun insert(movieObject: MovieObject)

    @Delete
    fun delete(movieObject: MovieObject)
}