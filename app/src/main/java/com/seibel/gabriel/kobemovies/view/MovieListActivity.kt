package com.seibel.gabriel.kobemovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seibel.gabriel.kobemovies.R
import com.seibel.gabriel.kobemovies.model.Movie

class MovieListActivity : AppCompatActivity(), MovieListFragment.OnMovieListInteractionListener {
    override fun onMovieSelected(movie: Movie) {
        println("Selected ${movie.title}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment.newInstance(1))
                .commitNow()
        }
    }
}
