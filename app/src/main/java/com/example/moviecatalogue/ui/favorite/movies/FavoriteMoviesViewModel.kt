package com.example.moviecatalogue.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class FavoriteMoviesViewModel(private val moviesTvRepository: MoviesTvRepository) : ViewModel() {
    fun getFavMovies(): LiveData<PagedList<MoviesEntity>> = moviesTvRepository.getFavMovies()
    fun insertFavMovies(moviesEntity: MoviesEntity) = moviesTvRepository.insertFavMovies(moviesEntity)
    fun delFavMovies(moviesEntity: MoviesEntity) = moviesTvRepository.deleteFavMovies(moviesEntity)
}