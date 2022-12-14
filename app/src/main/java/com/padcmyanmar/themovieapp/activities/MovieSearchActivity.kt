package com.padcmyanmar.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.themovieapp.R
import com.padcmyanmar.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(), MovieViewHolderDelegate {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MovieSearchActivity::class.java)
        }
    }

    //Adapter
    private lateinit var mMovieAdapter: MovieAdapter

    //Models
    private val mMovieModel = MovieModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        editTextSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({
                mMovieAdapter.setNewData(it)
            }, {
                showError(it.localizedMessage ?: "")
            })
    }

    private fun showError(errorMessage: String) {
        Snackbar.make(window.decorView, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    private fun setUpRecyclerView() {
        mMovieAdapter = MovieAdapter(this)
        rvMovies.adapter = mMovieAdapter
        rvMovies.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieID = movieId))
    }
}