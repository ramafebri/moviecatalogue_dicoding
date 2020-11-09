package com.example.moviecatalogue.service

import com.example.moviecatalogue.data.source.remote.NowPlayingResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("movie/now_playing?api_key=7b4115d0eb2b288c943ffcb79cdb36a7")
    fun getAllMovies(): Call<NowPlayingResponse>
}