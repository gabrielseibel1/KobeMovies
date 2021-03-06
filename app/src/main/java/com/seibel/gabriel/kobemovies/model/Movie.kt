package com.seibel.gabriel.kobemovies.model

import com.google.gson.annotations.SerializedName

data class Movie(val title: String,
                 val overview: String,
                 @SerializedName("poster_path") val posterPath: String)