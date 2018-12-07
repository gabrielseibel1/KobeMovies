package com.seibel.gabriel.kobemovies.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seibel.gabriel.kobemovies.api.QueryResult
import com.seibel.gabriel.kobemovies.api.RetrofitAPI
import com.seibel.gabriel.kobemovies.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel : ViewModel() {
    private lateinit var movies: MutableLiveData<List<Movie>>

    fun getUpcomingMovies(): LiveData<List<Movie>> {
        if (!::movies.isInitialized) {
            movies = MutableLiveData()
            loadUpcomingMovies()
        }
        return movies
    }

    private fun loadUpcomingMovies() {
        //create api object
        val retrofitAPI = RetrofitAPI()

        //configure call options
        val callOptions = mapOf(
            Pair("api_key", RetrofitAPI.API_KEY)
        )

        //create call
        val call = retrofitAPI.tmdbService().upcomingMovies(callOptions)

        //run it async
        call.enqueue(object : Callback<QueryResult<Movie>> {

            override fun onFailure(call: Call<QueryResult<Movie>>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<QueryResult<Movie>>, response: Response<QueryResult<Movie>>) {
                response.body()?.let {
                    println(response)
                    println(response.body())

                    //take movie list from query result, updating live data
                    val queryResult: QueryResult<Movie> = response.body()!!
                    movies.value = queryResult.results
                }
            }
        })
    }
}
