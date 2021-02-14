package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse

object DataDummy {
    private val jsonHelper = JsonHelper()

    fun generatePlayingMovies(): ArrayList<ResultsItemMovies?> {
        val movies = ArrayList<ResultsItemMovies?>()
        val moviesFromJson = jsonHelper.loadPlayingMovies()
        movies.addAll(moviesFromJson)
        return movies
    }

    fun generateDetailMovies(): MoviesDetailResponse {
        return jsonHelper.loadDetailMovies()
    }

    fun generatePopularTv(): ArrayList<ResultsItemTv?> {
        val tv = ArrayList<ResultsItemTv?>()
        val tvFromJson = jsonHelper.loadPopularTv()
        tv.addAll(tvFromJson)
        return tv
    }

    fun generateDetailTv(): TvDetailResponse {
        return jsonHelper.loadDetailTv()
    }
}