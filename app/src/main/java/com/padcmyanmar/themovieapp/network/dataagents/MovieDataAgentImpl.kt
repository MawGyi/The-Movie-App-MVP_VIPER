//package com.padcmyanmar.themovieapp.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.google.gson.Gson
//import com.padcmyanmar.themovieapp.data.vos.ActorVO
//import com.padcmyanmar.themovieapp.data.vos.GenreVO
//import com.padcmyanmar.themovieapp.data.vos.MovieVO
//import com.padcmyanmar.themovieapp.network.responses.MovieListResponse
//import com.padcmyanmar.themovieapp.utils.API_GET_NOW_PLAYING
//import com.padcmyanmar.themovieapp.utils.BASE_URL
//import com.padcmyanmar.themovieapp.utils.MOVIE_API_KEY
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.lang.Exception
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl : MovieDataAgent {
//    override fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        GetNowPlayingMovieTask().execute()
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
//
//    }
//
//    class GetNowPlayingMovieTask() : AsyncTask<Void, Void, MovieListResponse?>() {
//        //Background Thread
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//            val url: URL
//            var reader: BufferedReader? = null
//            val stringBuilder: StringBuilder
//
//            try {
//                //Create the HttpURLConnection
//                url =
//                    URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
//                val connection = url.openConnection() as HttpURLConnection
//
//                //set HTTP Method
//
//                connection.requestMethod = "GET" //3
//
//                // response time
//
//                connection.readTimeout = 15 * 1000
//
//                connection.doInput = true
//                connection.doOutput = false
//
//                connection.connect()
//
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                )
//                stringBuilder = StringBuilder()
//
//                for (line in reader.readLines()) {
//                    stringBuilder.append(line + "\n")
//                }
//                val responseString = stringBuilder.toString()
//                Log.d("NowPlayingMovies", responseString)
//
//                val movieListResponse = Gson().fromJson(
//                    responseString,
//                    MovieListResponse::class.java
//                )
//                return movieListResponse
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.e("NewsError", e.message ?: "")
//            } finally {
//                if (reader != null) {
//                    try {
//                        reader.close()
//                    } catch (ioe: IOException) {
//                        ioe.printStackTrace()
//                    }
//                }
//            }
//            return null
//        }
//
//        // Main Thread
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//}