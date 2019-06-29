package io.github.jitinsharma.reduxmovieexample

import android.app.Application
import androidx.room.Room
import com.squareup.leakcanary.LeakCanary
import io.github.jitinsharma.reduxmovieexample.data.local.MovieDatabase
import timber.log.Timber

/**
 * Created by jsharma on 15/01/18.
 */
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        instance = this
        debugMode { Timber.plant(Timber.DebugTree()) }
        movieDataBase = Room
                .databaseBuilder(this, MovieDatabase::class.java, "movieDB")
                .build()
    }

    companion object {
        @get:Synchronized lateinit var instance: MovieApplication
            private set
        var movieDataBase: MovieDatabase? = null
    }
}

inline fun debugMode(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}