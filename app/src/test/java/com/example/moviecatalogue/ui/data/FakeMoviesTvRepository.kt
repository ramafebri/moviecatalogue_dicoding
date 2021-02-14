package com.example.moviecatalogue.ui.data

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.MoviesTvDataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse

class FakeMoviesTvRepository(private val remoteDataSource: RemoteDataSource) : MoviesTvDataSource {
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
        TODO("Not yet implemented")
    }

    override fun getFavMoviesById(moviesId: Int): LiveData<MoviesEntity> {
        TODO("Not yet implemented")
    }

    override fun insertFavMovies(moviesEntity: MoviesEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteFavMovies(moviesEntity: MoviesEntity) {
        TODO("Not yet implemented")
    }

    override fun getFavTv(): LiveData<List<TvShowsEntity>> {
        TODO("Not yet implemented")
    }

    override fun getFavTvById(tvId: Int): LiveData<TvShowsEntity> {
        TODO("Not yet implemented")
    }

    override fun insertFavTv(tvEntity: TvShowsEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteFavTv(tvEntity: TvShowsEntity) {
        TODO("Not yet implemented")
    }
}