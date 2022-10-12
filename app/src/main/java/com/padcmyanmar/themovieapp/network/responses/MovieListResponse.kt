package com.padcmyanmar.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.themovieapp.data.vos.DateVO
import com.padcmyanmar.themovieapp.data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("dates")
    val dates: DateVO?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieVO>?
)