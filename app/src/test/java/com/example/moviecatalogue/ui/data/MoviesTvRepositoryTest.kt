package com.example.moviecatalogue.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.*

class MoviesTvRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieId = 659986
    private val tvId = 69050

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val moviesTvRepository = FakeMoviesTvRepository(remote)

    private val moviesResponses = DataDummy.generatePlayingMovies()
    private val tvResponses = DataDummy.generatePopularTv()

    private val detailMoviesResponses = DataDummy.generateDetailMovies()
    private val detailTvResponses = DataDummy.generateDetailTv()

    @Before
    fun setUp() {
        moviesTvRepository.setAllMovies()
        moviesTvRepository.setAllTv()
        moviesTvRepository.setDetailMovies(movieId)
        moviesTvRepository.setDetailTv(tvId)
    }

    @Test
    fun getAllMovies() {
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
}