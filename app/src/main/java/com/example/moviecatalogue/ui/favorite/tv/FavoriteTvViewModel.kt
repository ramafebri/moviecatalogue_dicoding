package com.example.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity

class FavoriteTvViewModel(private val moviesTvRepository: MoviesTvRepository) : ViewModel() {
    fun getFavTv(): LiveData<PagedList<TvShowsEntity>> = moviesTvRepository.getFavTv()
    fun insertFavTv(tvShowsEntity: TvShowsEntity) = moviesTvRepository.insertFavTv(tvShowsEntity)
    fun delFavTv(tvShowsEntity: TvShowsEntity) = moviesTvRepository.deleteFavTv(tvShowsEntity)
}