package com.ambrosio.movietrove.models

import com.google.gson.annotations.SerializedName

class DiscoverResponse {

    fun getListItems(): List<Movie>? { return results}

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("total_results")
    var totalResults: Int = 0

    @SerializedName("results")
    var results: ArrayList<Movie>? = ArrayList()
}