package com.example.moviecatalogue.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MoviesEntity
import com.example.moviecatalogue.data.TvShowsEntity
import com.example.moviecatalogue.utils.DataDummy

class TvShowsViewModel : ViewModel() {
    fun getTvShows(): List<TvShowsEntity> = DataDummy.generateTvShows()
}