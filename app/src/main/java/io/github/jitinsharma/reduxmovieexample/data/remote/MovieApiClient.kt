package io.github.jitinsharma.reduxmovieexample.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by jsharma on 18/01/18.
 */

object MovieApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
}