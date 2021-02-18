package com.example.moviecatalogue.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.ui.utils.LiveDataTestUtil
import com.example.moviecatalogue.ui.utils.PagedListUtil
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import java.util.*

class MoviesTvRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieId = 464052
    private val tvId = 69050

    private val moviesFavResponses = DataDummy.generateFavMovies()
    private val tvFavResponses = DataDummy.generateFavTv()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val moviesTvRepository = FakeMoviesTvRepository(remote, local, appExecutors)

    @Before
    fun setUp() {
        moviesTvRepository.setAllMovies()
        moviesTvRepository.setAllTv()
        moviesTvRepository.setDetailMovies(movieId)
        moviesTvRepository.setDetailTv(tvId)
    }

    @Test
    fun getAllMovies() {
        val moviesResponses = DataDummy.generatePlayingMovies()
        val movies = MutableLiveData<ArrayList<ResultsItemMovies?>>()
        movies.value = moviesResponses

        `when`(remote.getNowPlayingMovies()).thenReturn(movies)
        val moviesEntities = LiveDataTestUtil.getValue(moviesTvRepository.getAllMovies())
        verify(remote).getNowPlayingMovies()
        assertNotNull(moviesEntities)
        assertEquals(moviesResponses.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getAllTv() {
        val tvResponses = DataDummy.generatePopularTv()
        val tv = MutableLiveData<ArrayList<ResultsItemTv?>>()
        tv.value = tvResponses

        `when`(remote.getPopularTv()).thenReturn(tv)
        val tvEntities = LiveDataTestUtil.getValue(moviesTvRepository.getAllTv())
        verify(remote).getPopularTv()
        assertNotNull(tvEntities)
        assertEquals(tvResponses.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    fun getDetailMovies() {
        val detailMoviesResponses = DataDummy.generateDetailMovies()
        val detailMovies = MutableLiveData<MoviesDetailResponse>()
        detailMovies.value = detailMoviesResponses

        `when`(remote.getDetailMovies()).thenReturn(detailMovies)
        val detailMoviesEntities = LiveDataTestUtil.getValue(moviesTvRepository.getDetailMovies())
        verify(remote).getDetailMovies()
        assertNotNull(detailMoviesEntities)
        if (detailMoviesEntities != null) {
            assertEquals(detailMoviesResponses.title, detailMoviesEntities.title)
        }
    }

    @Test
    fun getDetailTv() {
        val detailTvResponses = DataDummy.generateDetailTv()
        val detailTv = MutableLiveData<TvDetailResponse>()
        detailTv.value = detailTvResponses

        `when`(remote.getDetailTv()).thenReturn(detailTv)
        val detailTvEntities = LiveDataTestUtil.getValue(moviesTvRepository.getDetailTv())
        verify(remote).getDetailTv()
        assertNotNull(detailTvEntities)
        if (detailTvEntities != null) {
            assertEquals(detailTvResponses.name, detailTvEntities.name)
        }
    }

    @Test
    fun getFavMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getFavMovies()).thenReturn(dataSourceFactory)
        moviesTvRepository.getFavMovies()

        val moviesEntities = PagedListUtil.mockPagedList(DataDummy.generateFavMovies())
        verify(local).getFavMovies()
        assertNotNull(moviesEntities)
        assertEquals(moviesFavResponses.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByIdFavMovies(){
        val movies = DataDummy.generateSingleFavMovies()
        moviesTvRepository.insertFavMovies(movies)
        verify(local).insertFavMovies(movies)

        val favMoviesData = MutableLiveData<MoviesEntity>()
        favMoviesData.value = movies
        `when`(local.getFavMoviesById(movieId)).thenReturn(favMoviesData)

        val favMoviesEntities = LiveDataTestUtil.getValue(moviesTvRepository.getFavMoviesById(movieId))
        verify(local).getFavMoviesById(movieId)
        assertNotNull(favMoviesEntities)
        if (favMoviesEntities != null) {
            assertEquals(movies.id, favMoviesEntities.id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAndGetByIdFavMovies(){
        val movies = DataDummy.generateSingleFavMovies()
        moviesTvRepository.insertFavMovies(movies)
        verify(local).insertFavMovies(movies)

        moviesTvRepository.deleteFavMovies(movies)
        verify(local).deleteFavMovies(movies)

        val favMoviesData = MutableLiveData<MoviesEntity>()
        favMoviesData.value = null
        `when`(local.getFavMoviesById(movieId)).thenReturn(favMoviesData)

        //Tidak memakai LiveDataTestUtil karna data null
        val favMoviesEntities = moviesTvRepository.getFavMoviesById(movieId).value
        verify(local).getFavMoviesById(movieId)

        assertNull(favMoviesEntities)
        assertEquals(favMoviesData.value, favMoviesEntities)
    }

    @Test
    fun getFavTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowsEntity>
        `when`(local.getFavTv()).thenReturn(dataSourceFactory)
        moviesTvRepository.getFavTv()

        val tvEntities = PagedListUtil.mockPagedList(DataDummy.generateFavTv())
        verify(local).getFavTv()
        assertNotNull(tvEntities)
        assertEquals(tvFavResponses.size.toLong(), tvEntities.size.toLong())
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByIdFavTv(){
        val tv = DataDummy.generateSingleFavTv()
        moviesTvRepository.insertFavTv(tv)
        verify(local).insertFavTv(tv)

        val favTvData = MutableLiveData<TvShowsEntity>()
        favTvData.value = tv
        `when`(local.getFavTvById(tvId)).thenReturn(favTvData)

        val favTvEntities = LiveDataTestUtil.getValue(moviesTvRepository.getFavTvById(tvId))
        verify(local).getFavTvById(tvId)
        assertNotNull(favTvEntities)
        if (favTvEntities != null) {
            assertEquals(tv.id, favTvEntities.id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteAndGetByIdFavTv(){
        val tv = DataDummy.generateSingleFavTv()
        moviesTvRepository.insertFavTv(tv)
        verify(local).insertFavTv(tv)

        moviesTvRepository.deleteFavTv(tv)
        verify(local).deleteFavTv(tv)

        val favTvData = MutableLiveData<TvShowsEntity>()
        favTvData.value = null
        `when`(local.getFavTvById(tvId)).thenReturn(favTvData)

        //Tidak memakai LiveDataTestUtil karna data null
        val favTvEntities = moviesTvRepository.getFavTvById(tvId).value
        verify(local).getFavTvById(tvId)

        assertNull(favTvEntities)
        assertEquals(favTvData.value, favTvEntities)
    }
}
