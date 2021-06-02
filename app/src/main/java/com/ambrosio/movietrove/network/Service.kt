package com.ambrosio.movietrove.network

import com.ambrosio.movietrove.models.DiscoverResponse
import com.ambrosio.movietrove.models.RecyclerList
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") key : String,
        @Query("page") page:Int
    ): DiscoverResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") key : String,
        @Query("page") page:Int
    ): DiscoverResponse
}