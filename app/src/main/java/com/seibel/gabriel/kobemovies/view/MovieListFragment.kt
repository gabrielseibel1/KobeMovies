package com.seibel.gabriel.kobemovies.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.seibel.gabriel.kobemovies.R
import com.seibel.gabriel.kobemovies.model.Movie

/**
 * A fragment representing a list of Movies.
 * Activities containing this fragment MUST implement the
 * [MovieListFragment.OnMovieListInteractionListener] interface.
 */
class MovieListFragment : Fragment() {

    private var columnCount = 1

    private var movies: List<Movie> = listOf()

    //private var listener: OnMovieListInteractionListener? = null

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_list_fragment, container, false)

        //configure UI
        if (view is CoordinatorLayout) {
            configureListAndViewModel(view)
            configureFloatingActionButton(view)
        }

        return view
    }

    private fun configureFloatingActionButton(view: CoordinatorLayout) {
        val fab: View = view.getChildAt(1)
        if (fab is FloatingActionButton) {

            //make fab update items when clicked
            fab.setOnClickListener {
                viewModel.loadUpcomingMovies()
            }
        }
    }

    private fun configureListAndViewModel(view: CoordinatorLayout) {
        //set the adapter and the model view
        val recycler: View = view.getChildAt(0)
        if (recycler is RecyclerView) {

            with(recycler) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MovieRecyclerViewAdapter(movies.toList(), context)
            }

            //get view model
            viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)

            //observe movies in recycler
            viewModel.getUpcomingMovies().observe(this, Observer<List<Movie>> { movies ->
                //update if observed data is non-null
                movies?.let {
                    (recycler.adapter as MovieRecyclerViewAdapter).setData(movies)
                }
            })
        }
    }

    companion object {

        //keys for fragment arguments
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int = 1) =
            MovieListFragment().apply {

                //pass arguments in bundle
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
