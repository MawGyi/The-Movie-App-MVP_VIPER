//package com.padcmyanmar.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import com.google.gson.Gson
//import com.padcmyanmar.themovieapp.data.vos.ActorVO
//import com.padcmyanmar.themovieapp.data.vos.GenreVO
//import com.padcmyanmar.themovieapp.data.vos.MovieVO
//import com.padcmyanmar.themovieapp.network.responses.MovieListResponse
//import com.padcmyanmar.themovieapp.utils.API_GET_NOW_PLAYING
//import com.padcmyanmar.themovieapp.utils.BASE_URL
//import com.padcmyanmar.themovieapp.utils.MOVIE_API_KEY
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.util.concurrent.TimeUnit
//
//
//object OkHttpDataAgentImpl : MovieDataAgent {
//
//    private val mClient: OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(15, TimeUnit.SECONDS)
//        .readTimeout(15, TimeUnit.SECONDS)
//        .writeTimeout(15, TimeUnit.SECONDS)
//        .build()
//
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMovieOkHttpTask(mClient).execute()
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
////        TODO("Not yet implemented")
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
////        TODO("Not yet implemented")
//    }
//
//    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
////        TODO("Not yet implemented")
//    }
//
//    override fun getMoviesByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
////        TODO("Not yet implemented")
//    }
//
//    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//
//    }
//
//    override fun getMovieDetails(
//        movieId: String?,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//
//    }
//
//    override fun getCreditsByMovie(
//        movieId: String?,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
////        TODO("Not yet implemented")
//    }
//
//    class GetNowPlayingMovieOkHttpTask(private val mOkHttpClient: OkHttpClient) :
//        AsyncTask<Void, Void, MovieListResponse>() {
//
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//
//            val request = Request.Builder()
//                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
//                .build()
//
//            try {
//                val response = mOkHttpClient.newCall(request).execute()
//
//                if (response.isSuccessful) {
//                    response.body?.let {
//                        val responseString = it.string()
//                        val response = Gson().fromJson(
//                            responseString,
//                            MovieListResponse::class.java
//                        )
//                        return response
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//}