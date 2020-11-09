package com.example.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import com.example.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Looper.myLooper()?.let { Handler(it) }

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        handler?.postDelayed({ callback.onAllMoviesReceived(jsonHelper.loadAllMovies()) }, SERVICE_LATENCY_IN_MILLIS)
        // jsonHelper.loadAllMovies()
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(courseResponses: List<ResultsItem>)
    }

}