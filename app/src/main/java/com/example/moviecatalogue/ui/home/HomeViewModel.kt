package com.example.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.utils.DataDummy

class HomeViewModel : ViewModel() {
    fun getMovies(): List<MoviesEntity> = DataDummy.generateMovies()
}