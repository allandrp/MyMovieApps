package com.alland.mymovieapps.core.data.remote

import com.alland.mymovieapps.core.data.remote.response.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("language") language: String = "id-ID"): MovieApiResponse

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("language") language: String = "id-ID"): MovieApiResponse
}