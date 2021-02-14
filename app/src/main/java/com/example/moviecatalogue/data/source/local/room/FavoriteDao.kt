package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favoritemovies")
    fun getMovies(): LiveData<List<MoviesEntity>>

    @Transaction
    @Query("SELECT * FROM favoritemovies WHERE moviesId = :moviesId")
    fun getMoviesById(moviesId: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: MoviesEntity)

    @Delete
    fun deleteMovies(movies: MoviesEntity)

    @Query("SELECT * FROM favoritetv")
    fun getTv(): LiveData<List<TvShowsEntity>>

    @Transaction
    @Query("SELECT * FROM favoritetv WHERE tvId = :tvId")
    fun getTvById(tvId: Int): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: TvShowsEntity)

    @Delete
    fun deleteTv(tv: TvShowsEntity)
}