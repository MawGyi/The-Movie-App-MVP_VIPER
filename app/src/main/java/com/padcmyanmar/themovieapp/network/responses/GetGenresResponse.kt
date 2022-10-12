package com.padcmyanmar.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.themovieapp.data.vos.GenreVO

data class GetGenresResponse(
    @SerializedName("genres")
    val genres: List<GenreVO>
)