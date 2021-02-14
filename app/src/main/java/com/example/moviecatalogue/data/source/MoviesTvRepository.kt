package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.utils.AppExecutors

class MoviesTvRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
)
    : MoviesTvDataSource {

    companion object {
        @Volatile
        private var instance: MoviesTvRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MoviesTvRepository =
            instance ?: synchronized(this) {
                instance ?: MoviesTvRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<ArrayList<ResultsItemMovies?>> {
        return remoteDataSource.getNowPlayingMovies()
    }

    override fun setAllMovies() {
        remoteDataSource.setNowPlayingMovies()
    }

    override fun getDetailMovies(): LiveData<MoviesDetailResponse?> {
        return remoteDataSource.getDetailMovies()
    }

    override fun setDetailMovies(id: Int) {
        remoteDataSource.setDetailMovies(id)
    }

    override fun getAllTv(): LiveData<ArrayList<ResultsItemTv?>> {
        return remoteDataSource.getPopularTv()
    }

    override fun setAllTv() {
        remoteDataSource.setPopularTv()
    }

    override fun getDetailTv(): LiveData<TvDetailResponse?> {
        return remoteDataSource.getDetailTv()
    }

    override fun setDetailTv(id: Int) {
        remoteDataSource.setDetailTv(id)
    }

    override fun getFavMovies(): LiveData<List<MoviesEntity>> {
        return localDataSource.getFavMovies()
    }

    override fun getFavMoviesById(moviesId: Int): LiveData<MoviesEntity> {
        return localDataSource.getFavMoviesById(moviesId)
    }

    override fun insertFavMovies(moviesEntity: MoviesEntity) {
        appExecutors.diskIO().execute { localDataSource.insertFavMovies(moviesEntity) }
    }

    override fun deleteFavMovies(moviesEntity: MoviesEntity) {
        appExecutors.diskIO().execute { localDataSource.deleteFavMovies(moviesEntity) }
    }

    override fun getFavTv(): LiveData<List<TvShowsEntity>> {
        return localDataSource.getFavTv()
    }

    override fun getFavTvById(tvId: Int): LiveData<TvShowsEntity> {
        return localDataSource.getFavTvById(tvId)
    }

    override fun insertFavTv(tvEntity: TvShowsEntity) {
        appExecutors.diskIO().execute { localDataSource.insertFavTv(tvEntity) }
    }

    override fun deleteFavTv(tvEntity: TvShowsEntity) {
        appExecutors.diskIO().execute { localDataSource.deleteFavTv(tvEntity) }
    }
}