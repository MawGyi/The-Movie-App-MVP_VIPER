package com.padcmyanmar.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ActorVO(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("known_for")
    val known_for: List<MovieVO>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profile_path: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("known_for_department")
    val known_for_department: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("cast_id")
    val castId: Int?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String?,
    @SerializedName("order")
    val order: Int?
)