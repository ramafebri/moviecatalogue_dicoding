package com.example.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.ui.favorite.movies.FavoriteMoviesViewModel
import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMoviesViewModelTest {
    private lateinit var viewModel: FavoriteMoviesViewModel
    private val movieId = 464052

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesTvRepository: MoviesTvRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMoviesViewModel(moviesTvRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyFavoriteMovies = pagedList
        `when`(dummyFavoriteMovies.size).thenReturn(2)
        val movies = MutableLiveData<PagedList<MoviesEntity>>()
        movies.value = dummyFavoriteMovies

        `when`(moviesTvRepository.getFavMovies()).thenReturn(movies)
        val moviesValue = viewModel.getFavMovies().value
        verify(moviesTvRepository).getFavMovies()

        assertNotNull(moviesValue)
        if (moviesValue != null) {
            assertEquals(2, moviesValue.size)
        }

        viewModel.getFavMovies().observeForever(observer)
        verify(observer).onChanged(dummyFavoriteMovies)
    }

    @Test
    fun insertAndGetByIdFavMovies(){
        val movies = DataDummy.generateSingleFavMovies()
        viewModel.insertFavMovies(movies)
        verify(moviesTvRepository).insertFavMovies(movies)

        val favMoviesData = MutableLiveData<MoviesEntity>()
        favMoviesData.value = movies
        `when`(moviesTvRepository.getFavMoviesById(movieId)).thenReturn(favMoviesData)

        val favMoviesEntities = moviesTvRepository.getFavMoviesById(movieId).value
        verify(moviesTvRepository).getFavMoviesById(movieId)

        assertNotNull(favMoviesEntities)
        if (favMoviesEntities != null) {
            assertEquals(movies.id, favMoviesEntities.id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAndGetByIdFavMovies(){
        val movies = DataDummy.generateSingleFavMovies()
        viewModel.insertFavMovies(movies)
        verify(moviesTvRepository).insertFavMovies(movies)

        viewModel.delFavMovies(movies)
        verify(moviesTvRepository).deleteFavMovies(movies)

        val favMoviesData = MutableLiveData<MoviesEntity?>()
        favMoviesData.value = null
        `when`(moviesTvRepository.getFavMoviesById(movieId)).thenReturn(favMoviesData)

        val favMoviesEntities = moviesTvRepository.getFavMoviesById(movieId).value
        verify(moviesTvRepository).getFavMoviesById(movieId)

        assertNull(favMoviesEntities)
    }
}