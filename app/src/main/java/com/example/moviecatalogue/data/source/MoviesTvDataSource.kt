package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse

interface MoviesTvDataSource {
    fun getAllMovies(): LiveData<ArrayList<ResultsItemMovies?>>
    fun setAllMovies()
    fun getDetailMovies(): LiveData<MoviesDetailResponse?>
    fun setDetailMovies(id: Int)
    fun getAllTv(): LiveData<ArrayList<ResultsItemTv?>>
    fun setAllTv()
    fun getDetailTv(): LiveData<TvDetailResponse?>
    fun setDetailTv(id: Int)
    fun getFavMovies(): LiveData<PagedList<MoviesEntity>>
    fun getFavMoviesById(moviesId: Int): LiveData<MoviesEntity?>
    fun insertFavMovies(moviesEntity: MoviesEntity)
    fun deleteFavMovies(moviesEntity: MoviesEntity)
    fun getFavTv(): LiveData<PagedList<TvShowsEntity>>
    fun getFavTvById(tvId: Int): LiveData<TvShowsEntity?>
    fun insertFavTv(tvEntity: TvShowsEntity)
    fun deleteFavTv(tvEntity: TvShowsEntity)
}