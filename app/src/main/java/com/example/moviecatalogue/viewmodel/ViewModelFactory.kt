package com.example.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.ui.detail.DetailViewModel
import com.example.moviecatalogue.ui.favorite.movies.FavoriteMoviesViewModel
import com.example.moviecatalogue.ui.favorite.tv.FavoriteTvViewModel
import com.example.moviecatalogue.ui.home.HomeViewModel
import com.example.moviecatalogue.ui.tvshows.TvShowsViewModel

class ViewModelFactory private constructor(private val mMoviesTvRepository: MoviesTvRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(mMoviesTvRepository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                return TvShowsViewModel(mMoviesTvRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMoviesTvRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMoviesViewModel::class.java) -> {
                return FavoriteMoviesViewModel(mMoviesTvRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvViewModel::class.java) -> {
                return FavoriteTvViewModel(mMoviesTvRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}