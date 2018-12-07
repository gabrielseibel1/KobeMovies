package com.seibel.gabriel.kobemovies.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.seibel.gabriel.kobemovies.R
import com.seibel.gabriel.kobemovies.api.RetrofitAPI
import com.seibel.gabriel.kobemovies.model.Movie


import com.seibel.gabriel.kobemovies.view.MovieListFragment.OnMovieListInteractionListener
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.movie.view.*
import org.w3c.dom.Text


/**
 * [RecyclerView.Adapter] that can display a [Movie] and makes a call to the
 * specified [OnMovieListInteractionListener].
 */
class MovieRecyclerViewAdapter(
    private var movies: List<Movie>,
    private val listener: OnMovieListInteractionListener?
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            listener?.onMovieSelected(item)
        }
    }

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
        holder.realeaseDate.text = movie.prettyReleaseDate()
        holder.overview.text = movie.overview

        //load image async with picasso
        Picasso.with(listener as Context)
            .load(RetrofitAPI.IMAGES_BASE_URL + movie.posterPath)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.poster)

        with(holder.mView) {
            tag = movie
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.title
        val realeaseDate: TextView = mView.release_date
        val overview: TextView = mView.overview
        val poster: ImageView = mView.poster

        override fun toString(): String {
            return super.toString() + " '" + overview.text + "'"
        }
    }
}
