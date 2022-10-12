package com.padcmyanmar.themovieapp.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.themovieapp.R
import com.padcmyanmar.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.themovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.themovieapp.data.vos.ActorVO
import com.padcmyanmar.themovieapp.data.vos.GenreVO
import com.padcmyanmar.themovieapp.data.vos.MovieVO
import com.padcmyanmar.themovieapp.mvp.presenters.MainPresenter
import com.padcmyanmar.themovieapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.themovieapp.mvp.views.MainView
import com.padcmyanmar.themovieapp.routers.navigateToMovieDetailsActivity
import com.padcmyanmar.themovieapp.routers.navigateToMovieSearchActivity
import com.padcmyanmar.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {

    //View Pods
    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowCaseAdapter: ShowCaseAdapter
    private lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    private lateinit var mMoviesByGenreViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: ActorListViewPod

    //Presenter
    private lateinit var mPresenter: MainPresenter

//    //Model
//    private val mMovieModel: MovieModel = MovieModelImpl
//
//    // Data
//    private var mGenres: List<GenreVO>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPresenter()

        setUpToolBar()
        setUpViewPods()
        setUpBannerViewPager()
//        setUpGenreTabLayout()
        setUpShowCaseRecyclerView()

        setUpListeners()

        //Request Data
//        requestData()

        mPresenter.onUiReady(this)

        // AsyncTask , inputStream
//        MovieDataAgentImpl.getNowPlayingMovies()

        //OkHttp , Request , Response
//        OkHttpDataAgentImpl.getNowPlayingMovies()

        //Retrofit
//        RetrofitDataAgentImpl.getNowPlayingMovies()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

//    private fun requestData() {
//        //Old
////        mMovieModel.getNowPlayingMovies(
////            onSuccess = {
////                mBannerAdapter.setNewData(it)
////            },
////            onFailure = {
////
////            }
////        )
//
//        //Now Playing Movies
//        mMovieModel.getNowPlayingMovies {
//            showError(it)
//        }?.observe(this, Observer {
//            mBannerAdapter.setNewData(it)
//        })
//
//
//        //Old
////        mMovieModel.getPopularMovies(
////            onSuccess = {
////                mBestPopularMovieListViewPod.setData(it)
////            },
////            onFailure = {
////                showError(it)
////            }
////        )
//
//        //Popular Movies
//        mMovieModel.getPopularMovies {
//            showError(it)
//        }?.observe(this, Observer {
//            mBestPopularMovieListViewPod.setData(it)
//        })
//
//        //Old
////        mMovieModel.getTopRatedMovies(
////            onSuccess = {
////                mShowCaseAdapter.setNewData(it)
////            },
////            onFailure = {
////                showError(it)
////            }
////        )
//
//        //Top Rated Movies
//        mMovieModel.getTopRatedMovies {
//            showError(it)
//        }?.observe(this, Observer {
//            mShowCaseAdapter.setNewData(it)
//        })
//
//        //Get Genres
//        mMovieModel.getGenres(
//            onSuccess = {
//                mGenres = it
//                setUpGenreTabLayout(it)
//
//                // Get Movies By Genre for First Genre
//                it.firstOrNull()?.id?.let { genreId ->
//                    getMoviesByGenre(genreId)
//                }
//            },
//            onFailure = {
//                showError(it)
//            }
//        )
//
//        //Get Actors
//        mMovieModel.getActors(
//            onSuccess = {
//                mActorListViewPod.setData(it)
//            },
//            onFailure = {
//                showError(it)
//            }
//        )
//
//    }

//    private fun getMoviesByGenre(genreId: Int) {
//        mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
//            onSuccess = {
//                mMoviesByGenreViewPod.setData(it)
//            },
//            onFailure = {
//                showError(it)
//            }
//        )
//    }

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowCaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenre(genreList: List<GenreVO>) {
        setUpGenreTabLayout(genreList)
    }

    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mMoviesByGenreViewPod.setData(moviesByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieId: Int) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieID = movieId))
        navigateToMovieDetailsActivity(movieId)
    }


    override fun showError(errorString: String) {
//        Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG).show()
    }

//    private fun showError(it: String) {
//        Snackbar.make(window.decorView, it, Snackbar.LENGTH_LONG).show()
//    }

    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)

        mMoviesByGenreViewPod = vpMoviesByGenre as MovieListViewPod
        mMoviesByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = vpActorsHomeScreen as ActorListViewPod

    }

    private fun setUpShowCaseRecyclerView() {
        mShowCaseAdapter = ShowCaseAdapter(mPresenter)
        rvShowCases.adapter = mShowCaseAdapter
        rvShowCases.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {
        //Genre Tab Layout
        tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                mGenres?.getOrNull(tab?.position ?: 0)?.id?.let {
//                    getMoviesByGenre(it)
//                }
                mPresenter.onTapGenre(tab?.position ?: 0)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        //Kotlin Scope Function
        genreList.forEach {
            tabLayoutGenre.newTab().apply {
                text = it.name
                tabLayoutGenre.addTab(this)
            }
        }
//       dummyGenreLists.forEach {
//           val tab = tabLayoutGenre.newTab()
//           tab.text=it
//           tabLayoutGenre.addTab(tab)
//       }
    }

    private fun setUpBannerViewPager() {

        mBannerAdapter = BannerAdapter(mPresenter)
        viewPagerBanner.adapter = mBannerAdapter

        dotsIndicatorBanner.attachTo(viewPagerBanner)

    }

    private fun setUpToolBar() {
        //App Bar Leading  Icon
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

//    override fun onTapMovieFromBanner(movieId: Int) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieID = movieId))
////        Snackbar.make(window.decorView, "$movieId", Snackbar.LENGTH_LONG).show()
//    }
//
//    override fun onTapMovieFromShowcase(movieId: Int) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieID = movieId))
////        Snackbar.make(window.decorView, "$movieId", Snackbar.LENGTH_LONG).show()
//    }
//
//    override fun onTapMovie(movieId: Int) {
//        startActivity(MovieDetailsActivity.newIntent(this, movieID = movieId))
////        Snackbar.make(window.decorView, "$movieId", Snackbar.LENGTH_LONG).show()
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.searchMovie -> {
//                startActivity(MovieSearchActivity.newIntent(this))
                navigateToMovieSearchActivity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

