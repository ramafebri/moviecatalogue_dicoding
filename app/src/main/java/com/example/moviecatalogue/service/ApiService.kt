package com.example.moviecatalogue.service

import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.NowPlayingResponse
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val apiKey = BuildConfig.MOVIEDB_KEY
interface ApiService {
    @GET("movie/now_playing?api_key=$apiKey")
    fun getAllMovies(): Call<NowPlayingResponse>

    @GET("movie/{movieId}?api_key=$apiKey")
    fun getDetailMovies(@Path("movieId") path: String): Call<MoviesDetailResponse>

    @GET("tv/{tvId}?api_key=$apiKey")
    fun getDetailTv(@Path("tvId") path: String): Call<TvDetailResponse>

    @GET("tv/popular?api_key=$apiKey")
    fun getAllTv(): Call<TvResponse>
}