package com.padcmyanmar.themovieapp.routers

import android.app.Activity
import com.padcmyanmar.themovieapp.activities.MovieDetailsActivity
import com.padcmyanmar.themovieapp.activities.MovieSearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId: Int) {
    startActivity(MovieDetailsActivity.newIntent(this, movieID = movieId))
}

fun Activity.navigateToMovieSearchActivity() {
    startActivity(MovieSearchActivity.newIntent(this))
}