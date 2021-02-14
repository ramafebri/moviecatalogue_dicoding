package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.ui.MockResponseFileReader
import com.example.moviecatalogue.utils.DataDummy
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val movieId = 659986
    private val tvId = 69050

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesTvRepository: MoviesTvRepository

    @Mock
    private lateinit var observerMovies: Observer<MoviesDetailResponse?>

    @Mock
    private lateinit var observerTv: Observer<TvDetailResponse?>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(moviesTvRepository)
        viewModel.setDetailMovies(movieId)
        viewModel.setDetailTv(tvId)

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `get detail movies and check response Code 200 returned`(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("response_detail_movies.json").content)
        mockWebServer.enqueue(response)
        val  actualResponse = ApiConfig.getApiService().getDetailMovies(movieId.toString()).execute()
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `get detail movies and check id`(){
        val  actualResponse = ApiConfig.getApiService().getDetailMovies(movieId.toString()).execute()
        assertEquals(movieId, actualResponse.body()?.id)
    }

    @Test
    fun `get detail tv and check response Code 200 returned`(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("response_detail_tv.json").content)
        mockWebServer.enqueue(response)
        val  actualResponse = ApiConfig.getApiService().getDetailTv(tvId.toString()).execute()
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `get detail tv and check id`(){
        val  actualResponse = ApiConfig.getApiService().getDetailTv(tvId.toString()).execute()
        assertEquals(tvId, actualResponse.body()?.id)
    }

    @Test
    fun getDetailMovies() {
        val dummyDetailMovies = DataDummy.generateDetailMovies()
        val movies = MutableLiveData<MoviesDetailResponse?>()
        movies.value = dummyDetailMovies

        Mockito.`when`(moviesTvRepository.getDetailMovies()).thenReturn(movies)
        val detailMoviesValue = viewModel.getDetailMovies().value
        Mockito.verify(moviesTvRepository).getDetailMovies()

        assertNotNull(detailMoviesValue)
        if (detailMoviesValue != null) {
            assertEquals(2, detailMoviesValue.genres?.size)
        }

        viewModel.getDetailMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(dummyDetailMovies)
    }

    @Test
    fun getDetailTv() {
        val dummyDetailTv = DataDummy.generateDetailTv()
        val tv = MutableLiveData<TvDetailResponse?>()
        tv.value = dummyDetailTv

        Mockito.`when`(moviesTvRepository.getDetailTv()).thenReturn(tv)
        val detailTvValue = viewModel.getDetailTv().value
        Mockito.verify(moviesTvRepository).getDetailTv()

        assertNotNull(detailTvValue)
        if (detailTvValue != null) {
            assertEquals(3, detailTvValue.genres?.size)
        }

        viewModel.getDetailTv().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummyDetailTv)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}