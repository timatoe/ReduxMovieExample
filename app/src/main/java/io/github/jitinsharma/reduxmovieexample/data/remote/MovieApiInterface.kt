package io.github.jitinsharma.reduxmovieexample.data.remote

import io.github.jitinsharma.reduxmovieexample.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by jsharma on 18/01/18.
 */

interface MovieApiInterface {
    //api.themoviedb.org/3/discover/movie?sort_by=popularity.desc
    @GET("movie/top_rated")
    fun getTopRatedMovies(
            @Query("api_key")
            apiKey: String
    ): MovieResponse

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun discoverMovies(
            @Query("api_key")
            apiKey: String
    ): MovieResponse

    @GET("movie/{id}")
    fun getMovieDetails(
            @Path("id") id: Int,
            @Query("api_key") apiKey: String
    ): MovieResponse
}