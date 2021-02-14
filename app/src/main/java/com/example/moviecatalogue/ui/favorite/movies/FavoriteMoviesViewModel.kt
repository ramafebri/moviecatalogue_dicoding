package com.example.moviecatalogue.ui.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity

class FavoriteMoviesViewModel(private val moviesRepository: MoviesTvRepository) : ViewModel() {
    fun getFavMovies(): LiveData<List<MoviesEntity>> = moviesRepository.getFavMovies()
}