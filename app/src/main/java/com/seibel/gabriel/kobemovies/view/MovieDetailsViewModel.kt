package com.seibel.gabriel.kobemovies.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.seibel.gabriel.kobemovies.model.Movie

class MovieDetailsViewModel : ViewModel() {
    private lateinit var movie: MutableLiveData<Movie>

    fun getMovie(): LiveData<Movie> {
        if (!::movie.isInitialized) {
            movie = MutableLiveData()
        }
        return movie
    }

    fun setMovie(newMovie: Movie) {
        if (!::movie.isInitialized) {
            movie = MutableLiveData()
        }
        this.movie.value = newMovie
    }
}
