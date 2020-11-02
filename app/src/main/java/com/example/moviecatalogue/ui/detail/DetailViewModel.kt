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

    fun getDetailMovies(): MoviesEntity {
        lateinit var movies: MoviesEntity
        val moviesEntities = DataDummy.generateMovies()
        for (moviesEntity in moviesEntities) {
            if (moviesEntity.moviesId == moviesId) {
                movies = moviesEntity
            }
        }
        return movies
    }

    fun getDetailTvShows(): TvShowsEntity {
        lateinit var tvShows: TvShowsEntity
        val tvShowsEntities = DataDummy.generateTvShows()
        for (tvShowsEntity in tvShowsEntities) {
            if (tvShowsEntity.tvShowsId == tvId) {
                tvShows = tvShowsEntity
            }
        }
        return tvShows
    }
}