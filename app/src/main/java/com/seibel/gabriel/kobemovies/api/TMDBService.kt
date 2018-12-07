package com.seibel.gabriel.kobemovies.api

import com.seibel.gabriel.kobemovies.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TMDBService {

    @GET("movie/upcoming")
    fun upcomingMovies(@QueryMap urlParameters: Map<String, String>): Call<QueryResult<Movie>>
}