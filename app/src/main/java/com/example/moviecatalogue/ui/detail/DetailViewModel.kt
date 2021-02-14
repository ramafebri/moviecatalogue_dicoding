package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse

class DetailViewModel(private val moviesTvRepository: MoviesTvRepository) : ViewModel() {
    fun getDetailMovies(): LiveData<MoviesDetailResponse?> = moviesTvRepository.getDetailMovies()
    fun setDetailMovies(id: Int) = moviesTvRepository.setDetailMovies(id)
    fun getDetailTv(): LiveData<TvDetailResponse?> = moviesTvRepository.getDetailTv()
    fun setDetailTv(id: Int) = moviesTvRepository.setDetailTv(id)

    fun getFavMoviesById(id: Int): LiveData<MoviesEntity> = moviesTvRepository.getFavMoviesById(id)
    fun insertFavMovies(moviesEntity: MoviesEntity) = moviesTvRepository.insertFavMovies(moviesEntity)
    fun delFavMovies(moviesEntity: MoviesEntity) = moviesTvRepository.deleteFavMovies(moviesEntity)
    fun getFavTvById(id: Int): LiveData<TvShowsEntity> = moviesTvRepository.getFavTvById(id)
    fun insertFavTv(tvShowsEntity: TvShowsEntity) = moviesTvRepository.insertFavTv(tvShowsEntity)
    fun delFavTv(tvShowsEntity: TvShowsEntity) = moviesTvRepository.deleteFavTv(tvShowsEntity)
}