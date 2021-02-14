package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.FavoriteDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MoviesTvRepository {

        val database = FavoriteDatabase.getInstance(context)
        val localDataSource = LocalDataSource.getInstance(database.favoriteDao())
        val remoteDataSource = RemoteDataSource()
        val appExecutors = AppExecutors()

        return MoviesTvRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}