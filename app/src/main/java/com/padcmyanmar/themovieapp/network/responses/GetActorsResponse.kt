package com.padcmyanmar.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.themovieapp.data.vos.ActorVO

data class GetActorsResponse(
    @SerializedName("results")
    val results: List<ActorVO>?
)