package com.padcmyanmar.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.themovieapp.R
import com.padcmyanmar.themovieapp.data.vos.MovieVO
import com.padcmyanmar.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.themovieapp.viewholders.ShowCaseViewHolder

class ShowCaseAdapter(private val mDelegate: ShowcaseViewHolderDelegate): RecyclerView.Adapter<ShowCaseViewHolder>() {

    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_show_case,parent,false)
        return ShowCaseViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty()) {
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }
}