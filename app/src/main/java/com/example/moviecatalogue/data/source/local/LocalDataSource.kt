package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.local.room.FavoriteDao

class LocalDataSource private constructor(private val mFavoriteDao: FavoriteDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(favoriteDao: FavoriteDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(favoriteDao)
    }

    fun getFavMovies(): DataSource.Factory<Int, MoviesEntity> = mFavoriteDao.getMovies()

    fun getFavMoviesById(moviesId: Int): LiveData<MoviesEntity?> = mFavoriteDao.getMoviesById(moviesId)

    fun insertFavMovies(moviesEntity: MoviesEntity) = mFavoriteDao.insertMovies(moviesEntity)

    fun deleteFavMovies(moviesEntity: MoviesEntity) = mFavoriteDao.deleteMovies(moviesEntity)

    fun getFavTv(): DataSource.Factory<Int, TvShowsEntity> = mFavoriteDao.getTv()

    fun getFavTvById(tvId: Int): LiveData<TvShowsEntity?> = mFavoriteDao.getTvById(tvId)

    fun insertFavTv(tvShowsEntity: TvShowsEntity) = mFavoriteDao.insertTv(tvShowsEntity)

    fun deleteFavTv(tvShowsEntity: TvShowsEntity) = mFavoriteDao.deleteTv(tvShowsEntity)
}