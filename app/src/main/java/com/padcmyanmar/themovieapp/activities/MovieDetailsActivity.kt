package com.padcmyanmar.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.themovieapp.R
import com.padcmyanmar.themovieapp.data.models.MovieModel
import com.padcmyanmar.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.themovieapp.data.vos.ActorVO
import com.padcmyanmar.themovieapp.data.vos.GenreVO
import com.padcmyanmar.themovieapp.data.vos.MovieVO
import com.padcmyanmar.themovieapp.mvp.presenters.MovieDetailsPresenter
import com.padcmyanmar.themovieapp.mvp.presenters.MovieDetailsPresenterImpl
import com.padcmyanmar.themovieapp.mvp.views.MovieDetailsView
import com.padcmyanmar.themovieapp.utils.IMAGE_BASE_URL
import com.padcmyanmar.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_movie_details.*


class MovieDetailsActivity : AppCompatActivity(), MovieDetailsView {

//    //Model
//    private val mMovieModel: MovieModel = MovieModelImpl

    companion object {

        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newIntent(context: Context, movieID: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieID)
            return intent
        }
    }

    //View Pods
    private lateinit var actorsViewPod: ActorListViewPod
    private lateinit var creatorsViewPod: ActorListViewPod

    //Presenter
    private lateinit var mPresenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        setUpPresenter()

        setUpViewPods()
        setUpListeners()

        val movieID = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)

//        Snackbar.make(window.decorView, "$EXTRA_MOVIE_ID", Snackbar.LENGTH_LONG).show()

        movieID?.let {
//            requestData(it)
            mPresenter.onUiReadyInMovieDetails(this, movieID)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MovieDetailsPresenterImpl::class.java]
        mPresenter.initView(this)
    }

//    private fun requestData(movieID: Int) {
//
////        mMovieModel.getMovieDetails(
////            movieId = movieID.toString(),
////            onSuccess = {
////                bindData(it)
////            },
////            onFailure = {
////                showError(it)
////            }
////        )
//        mMovieModel.getMovieDetails(
//            movieId = movieID.toString(),
//            onFailure = { showError(it) }
//        )?.observe(this, Observer {
//            it?.let { movieDetails ->
//                bindData(movieDetails)
//            }
//        })
//
//        mMovieModel.getCreditsByMovie(
//            movieId = movieID.toString(),
//            onSuccess = {
//                actorsViewPod.setData(it.first)
//                creatorsViewPod.setData(it.second)
//            },
//            onFailure = {
//                showError(it)
//            }
//        )
//    }

    override fun showMovieDetails(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>) {
        actorsViewPod.setData(cast)
        creatorsViewPod.setData(crew)
    }

    override fun navigateBack() {
        finish()
    }

    override fun showError(errorString: String) {

    }

//    private fun showError(it: String) {
//        Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG).show()
//    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivMovieDetails)
        tvMovieName.text = movie.title ?: ""
        tvMovieReleaseYear.text = movie.releaseDate?.substring(0, 4)
        tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?.let {
            tvNumberOfVotes.text = "$it VOTES"
        }
        rbRatingMovieDetails.rating = movie.getRatingBaseOnFiveStars()
        bindGenres(movie, movie.genres ?: listOf())

        tvOverView.text = movie.overView ?: ""
        tvOriginalTitle.text = movie.originalTitle ?: ""
        collapsingTb.title = movie.originalTitle ?: ""
        tvType.text = movie.getGenresAsCommaSeparatedString()
        tvProduction.text = movie.getProductionCountriesAsCommaSeparatedString()
        tvPremiere.text = movie.releaseDate ?: ""
        tvDescription.text = movie.overView ?: ""
//        tvTime.text= movie.runTime?.toString() ?:""

    }

    private fun bindGenres(movie: MovieVO, genres: List<GenreVO>) {
        movie.genres?.count()?.let {
            tvFirstGenre.text = genres.firstOrNull()?.name ?: ""
            tvSecondGenre.text = genres.getOrNull(1)?.name ?: ""
            tvThirdGenre.text = genres.getOrNull(2)?.name ?: ""

            if (it < 3) {
                tvThirdGenre.visibility = View.GONE
            } else if (it < 2) {
                tvSecondGenre.visibility = View.GONE
            }
        }
    }


    private fun setUpListeners() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = vpActors as ActorListViewPod
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )

        creatorsViewPod = vpCreators as ActorListViewPod
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }
}