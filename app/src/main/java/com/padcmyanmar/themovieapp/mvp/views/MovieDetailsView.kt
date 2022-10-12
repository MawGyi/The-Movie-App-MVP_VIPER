package com.padcmyanmar.themovieapp.mvp.views

import com.padcmyanmar.themovieapp.data.vos.ActorVO
import com.padcmyanmar.themovieapp.data.vos.MovieVO

interface MovieDetailsView : BaseView {
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}