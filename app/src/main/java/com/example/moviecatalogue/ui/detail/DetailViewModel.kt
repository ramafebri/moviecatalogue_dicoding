package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.data.TvShowsEntity
import com.example.moviecatalogue.utils.DataDummy

class DetailViewModel: ViewModel() {
    private lateinit var moviesId: String
    private lateinit var tvId: String

    fun setSelectedDetail(dataId: String, isMovies: Boolean) {
        if(isMovies){
            this.moviesId = dataId
        } else {
            this.tvId = dataId
        }
    }

    fun getDetailMovies(): MoviesEntity? {
        var movies: MoviesEntity? = null
        val moviesEntities = DataDummy.generateMovies()
        for (moviesEntity in moviesEntities) {
            if (moviesEntity.moviesId == moviesId) {
                movies = moviesEntity
            }
        }
        return movies
    }

    fun getDetailTvShows(): TvShowsEntity? {
        var tvShows: TvShowsEntity? = null
        val tvShowsEntities = DataDummy.generateTvShows()
        for (tvShowsEntity in tvShowsEntities) {
            if (tvShowsEntity.tvShowsId == tvId) {
                tvShows = tvShowsEntity
            }
        }
        return tvShows
    }
}