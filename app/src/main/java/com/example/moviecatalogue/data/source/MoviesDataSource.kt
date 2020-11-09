package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.remote.ResultsItem

interface MoviesDataSource {
    fun getAllMovies(): LiveData<ArrayList<ResultsItem>>
}