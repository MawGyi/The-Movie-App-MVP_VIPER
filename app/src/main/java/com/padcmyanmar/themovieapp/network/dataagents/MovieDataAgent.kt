//package com.padcmyanmar.themovieapp.network.dataagents
//
//import com.padcmyanmar.themovieapp.data.vos.ActorVO
//import com.padcmyanmar.themovieapp.data.vos.GenreVO
//import com.padcmyanmar.themovieapp.data.vos.MovieVO
//
//interface MovieDataAgent {
//    fun getNowPlayingMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getPopularMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getGenres(
//        onSuccess: (List<GenreVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getMoviesByGenre(
//        genreId: String,  // -> ViewLayer
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getActors(
//        onSuccess: (List<ActorVO>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getMovieDetails(
//        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    )
//
//    fun getCreditsByMovie(
//        movieId: String?,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    )
//}
