package com.padcmyanmar.themovieapp.data.models


import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.padcmyanmar.themovieapp.data.vos.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


object MovieModelImpl : BaseModel(), MovieModel {
    //MovieDataAgent = InterfaceType , Retro -> Implementation Class
//    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    //DataBase
//    private var mMovieDataBase: MovieDataBase? = null

//    fun initDataBase(context: Context) {
//        mMovieDataBase = MovieDataBase.getDBInstance(context)
//    }

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

//        //DataBase
//        onSuccess(mMovieDataBase?.movieDao()?.getMovieByType(type = NOW_PLAYING) ?: listOf())

        //NetWork
        mMovieApi.getNowPlayingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.forEach { movies -> movies.type = NOW_PLAYING }
                    mMovieDataBase?.movieDao()?.insertMovies(it.results ?: listOf())

//                    onSuccess(it.results ?: listOf())
                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
        return mMovieDataBase?.movieDao()?.getMovieByType(type = NOW_PLAYING)
    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //DataBase
//        onSuccess(mMovieDataBase?.movieDao()?.getMovieByType(type = POPULAR) ?: listOf())

        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.forEach { movies -> movies.type = POPULAR }
                    mMovieDataBase?.movieDao()?.insertMovies(it.results ?: listOf())

//                    onSuccess(it.results ?: listOf())
                }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )
        return mMovieDataBase?.movieDao()?.getMovieByType(type = POPULAR)
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

        //DataBase
//        onSuccess(mMovieDataBase?.movieDao()?.getMovieByType(type = TOP_RATED) ?: listOf())

        mMovieApi.getTopRatedMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it.results?.forEach { movies -> movies.type = TOP_RATED }
                    mMovieDataBase?.movieDao()?.insertMovies(it.results ?: listOf())

//                    onSuccess(it.results ?: listOf())
                }, {
                    onFailure(it.localizedMessage ?: "")
                })
        return mMovieDataBase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }

    @SuppressLint("CheckResult")
    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getActors(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {

        //DataBase
//        val mMovieFromDatabase = mMovieDataBase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//        mMovieFromDatabase?.let {
//            onSuccess(it)
//        }

        //Network
        mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync =
                    mMovieDataBase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDataBase?.movieDao()?.insertSingleMovie(it)
//                onSuccess(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mMovieDataBase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    @SuppressLint("CheckResult")
    override fun getCreditsByMovie(
        movieId: String?,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieApi
            .searchMovie(query = query)
            .map { it.results ?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }


}