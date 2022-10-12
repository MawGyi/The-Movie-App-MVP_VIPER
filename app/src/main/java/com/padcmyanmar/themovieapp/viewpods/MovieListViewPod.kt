package com.padcmyanmar.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.themovieapp.adapters.MovieAdapter
import com.padcmyanmar.themovieapp.data.vos.MovieVO
import com.padcmyanmar.themovieapp.delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate : MovieViewHolderDelegate

    override fun onFinishInflate() {
//        setUpMovieRecyclerView()
        super.onFinishInflate()
    }

    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpMovieRecyclerView()
    }

    fun setData(movieList: List<MovieVO>) {
        mMovieAdapter.setNewData(movieList)
    }

    private fun setDelegate(delegate: MovieViewHolderDelegate) {
        this.mDelegate = delegate
    }

    private fun setUpMovieRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}