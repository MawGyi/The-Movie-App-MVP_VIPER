package com.padcmyanmar.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.themovieapp.mvp.views.MovieDetailsView

interface MovieDetailsPresenter : IBasePresenter {
    fun initView(view: MovieDetailsView)
    fun onUiReadyInMovieDetails(owner: LifecycleOwner, movieId: Int)
    fun onTapBack()
}