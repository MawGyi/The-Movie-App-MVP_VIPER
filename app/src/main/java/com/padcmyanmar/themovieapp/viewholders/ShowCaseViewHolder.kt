package com.padcmyanmar.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.themovieapp.data.vos.MovieVO
import com.padcmyanmar.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_show_case.view.*


class ShowCaseViewHolder(itemView: View, private val mDelegate: ShowcaseViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovieVo: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovieVo?.let {
                mDelegate.onTapMovieFromShowcase(it.id)
            }

        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVo = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCase)

        itemView.tvShowCaseMovieName.text = movie.title
        itemView.tvShowCaseMovieDate.text = movie.releaseDate
    }
}