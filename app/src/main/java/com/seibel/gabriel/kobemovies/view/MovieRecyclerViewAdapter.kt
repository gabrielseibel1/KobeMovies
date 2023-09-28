package com.seibel.gabriel.kobemovies.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.seibel.gabriel.kobemovies.R
import com.seibel.gabriel.kobemovies.api.RetrofitAPI
import com.seibel.gabriel.kobemovies.model.Movie

import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.movie.view.*


/**
 * [RecyclerView.Adapter] that can display a [Movie] list
 */
class MovieRecyclerViewAdapter(
    private var movies: List<Movie>,
    private val context: Context
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie, parent, false)
        return ViewHolder(view)
    }

    fun setData(newData: List<Movie>) {
        movies = newData
        notifyDataSetChanged()

        println(movies)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        //bind text fields
        holder.title.text = movie.title
        holder.popularity.text = "%.1f".format(movie.popularity)
        holder.releaseDate.text = movie.styledReleaseDate()

        //load image async with picasso
        Picasso.with(context)
            .load(RetrofitAPI.IMAGES_BASE_URL + movie.posterPath)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.poster)

        with(holder.mView) {
            tag = movie

            //navigate to other destination (movie details)
            setOnClickListener {
                val directions = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment()
                directions.setMovie(Gson().toJson(movies[position]))
                Navigation.findNavController(this).navigate(directions)
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.title
        val popularity: TextView = mView.popularity
        val releaseDate: TextView = mView.release_date
        val poster: ImageView = mView.poster

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }
}
