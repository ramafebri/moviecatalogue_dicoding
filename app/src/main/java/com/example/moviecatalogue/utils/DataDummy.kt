package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
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

    fun generateFavMovies(): List<MoviesEntity>{
        val movies = ArrayList<MoviesEntity>()

        movies.add(MoviesEntity(
                464052,
                "Wonder Woman 1984",
                "2020-12-16",
                "en",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg"
        ))

        movies.add(MoviesEntity(
                602269,
                "The Little Things",
                "2021-01-27",
                "en",
                "/c7VlGCCgM9GZivKSzBgzuOVxQn7.jpg"
        ))

        return movies
    }

    fun generateSingleFavMovies(): MoviesEntity{
        return MoviesEntity(
            464052,
            "Wonder Woman 1984",
            "2020-12-16",
            "en",
            "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg")
    }

    fun generateSingleFavTv(): TvShowsEntity{
        return TvShowsEntity(
            69050,
            "Riverdale",
            "2017-01-26",
            "en",
            "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg")
    }

    fun generateFavTv(): List<TvShowsEntity>{
        val tv = ArrayList<TvShowsEntity>()

        tv.add(TvShowsEntity(
                69050,
                "Riverdale",
                "2017-01-26",
                "en",
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"
        ))

        tv.add(TvShowsEntity(
                85271,
                "WandaVision",
                "2021-01-15",
                "en",
                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg"
        ))

        return tv
    }
}