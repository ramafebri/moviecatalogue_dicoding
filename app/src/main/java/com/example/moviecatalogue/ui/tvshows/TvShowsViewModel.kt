package com.example.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv

class TvShowsViewModel(private val moviesRepository: MoviesTvRepository) : ViewModel() {
    fun getTvShows(): LiveData<ArrayList<ResultsItemTv?>> = moviesRepository.getAllTv()
    fun setTvShows() = moviesRepository.setAllTv()
}