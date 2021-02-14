package com.example.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies

class HomeViewModel(private val moviesRepository: MoviesTvRepository) : ViewModel() {
    fun getPlayingMovies(): LiveData<ArrayList<ResultsItemMovies?>> = moviesRepository.getAllMovies()
    fun setPlayingMovies() = moviesRepository.setAllMovies()
}