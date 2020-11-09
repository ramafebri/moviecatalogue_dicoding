package com.example.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.MoviesRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.remote.ResultsItem
import com.example.moviecatalogue.utils.DataDummy

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    fun getMovies(): LiveData<ArrayList<ResultsItem>> = moviesRepository.getAllMovies()
}