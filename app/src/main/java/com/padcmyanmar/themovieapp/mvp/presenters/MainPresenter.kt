package com.padcmyanmar.themovieapp.mvp.presenters

import com.padcmyanmar.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.themovieapp.mvp.views.MainView

interface MainPresenter : IBasePresenter, BannerViewHolderDelegate, ShowcaseViewHolderDelegate,
    MovieViewHolderDelegate {
    fun initView(view: MainView)
    fun onTapGenre(genrePosition: Int)
}