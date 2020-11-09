package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.ResultsItem

class MoviesRepository private constructor(private val remoteDataSource: RemoteDataSource) : MoviesDataSource {

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MoviesRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesRepository(remoteData)
            }
    }

    override fun getAllMovies(): LiveData<ArrayList<ResultsItem>> {
        val moviesResults = MutableLiveData<ArrayList<ResultsItem>>()
//        val data = remoteDataSource.getAllCourses()
//        moviesResults.postValue(data)
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(courseResponses: List<ResultsItem>) {
                moviesResults.postValue(courseResponses as ArrayList<ResultsItem>?)
            }
        })
        return moviesResults
    }
}