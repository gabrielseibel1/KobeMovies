package com.seibel.gabriel.kobemovies.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.gson.Gson

import com.seibel.gabriel.kobemovies.R
import com.seibel.gabriel.kobemovies.api.RetrofitAPI
import com.seibel.gabriel.kobemovies.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_details_fragment.*

class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.movie_details_fragment, container, false)

        view.let {

            //get view model
            viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

            //observe movies in recycler
            viewModel.getMovie().observe(this, Observer<Movie> { movie ->
                //update if observed data is non-null
                /*movies?.let {
                    (recycler.adapter as MovieRecyclerViewAdapter).setData(movies)
                }*/
                println("Movie is $movie")

                updateUIWithMovie(movie)
            })

            //get movie argument and initialize view model value
            val movieJsonFromArguments = MovieDetailsFragmentArgs.fromBundle(arguments).movie
            viewModel.setMovie(Gson().fromJson(movieJsonFromArguments, Movie::class.java))
        }

        return view
    }

    private fun updateUIWithMovie(movie: Movie) {
        //bind text fields
        title.text = movie.title
        release_date.text = movie.styledReleaseDate()
        overview.text = movie.overview


        //load image async with picasso
        Picasso.with(context)
            .load(RetrofitAPI.IMAGES_HIGH_RES_BASE_URL + movie.backdropPath)
            .error(R.drawable.ic_launcher_foreground)
            .into(backdrop)
    }
}
