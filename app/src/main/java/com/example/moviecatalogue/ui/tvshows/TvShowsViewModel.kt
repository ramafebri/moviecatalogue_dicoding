package com.example.moviecatalogue.ui.tvshows

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.utils.DataDummy

class TvShowsViewModel : ViewModel() {
    fun getTvShows(): List<TvShowsEntity> = DataDummy.generateTvShows()
}