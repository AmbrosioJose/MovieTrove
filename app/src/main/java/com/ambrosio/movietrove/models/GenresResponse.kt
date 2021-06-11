package com.ambrosio.movietrove.models

import com.google.gson.annotations.SerializedName

class GenresResponse {

    @SerializedName("genres")
    val genres: List<Genre> = listOf()
}