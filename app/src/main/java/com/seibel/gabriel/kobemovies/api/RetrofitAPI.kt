package com.seibel.gabriel.kobemovies.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPI {

    //choose OKHttp client, with body interceptor
    private val httpClient = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    ).build()

    //create retrofit object
    private val retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun tmdbService() = retrofit.create(TMDBService::class.java)


    companion object {
        const val API_KEY = "c5850ed73901b8d268d0898a8a9d8bff"

        const val API_BASE_URL = "https://api.themoviedb.org/3/"

        const val IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    }
}