package com.seibel.gabriel.kobemovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("release_date") val releaseDate: String,
    val popularity: Float
) {
    fun styledReleaseDate() = "Releases ${slashFormattedReleaseDate()}"

    private fun slashFormattedReleaseDate(): String {
        val tokens = releaseDate.splitToSequence("-").toList()

        val year = tokens[0]
        val month= tokens[1]
        val day = tokens[2]

        return "$month/$day/$year"
    }
}