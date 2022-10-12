package com.padcmyanmar.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.themovieapp.data.vos.ActorVO

class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?
)