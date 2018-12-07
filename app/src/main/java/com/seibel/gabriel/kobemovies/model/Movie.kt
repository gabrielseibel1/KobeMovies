package com.seibel.gabriel.kobemovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
) {
    fun prettyReleaseDate(): String {
        val tokens = releaseDate.splitToSequence("-").toList()

        val year = tokens[0]
        val month= tokens[1]
        val day = tokens[2]

        return "Release date: $month/$day/$year"
    }
}