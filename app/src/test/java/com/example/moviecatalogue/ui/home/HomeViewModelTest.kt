package com.example.moviecatalogue.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.remote.response.ResultsItemMovies
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.ui.utils.MockResponseFileReader
import com.example.moviecatalogue.utils.DataDummy
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesTvRepository: MoviesTvRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<ResultsItemMovies?>>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(moviesTvRepository)
        viewModel.setPlayingMovies()

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `get playing movies and check response Code 200 returned`(){
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockResponseFileReader("response_movies.json").content)
        mockWebServer.enqueue(response)
        val  actualResponse = ApiConfig.getApiService().getAllMovies().execute()
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `get playing movies and check pages size`(){
        val  actualResponse = ApiConfig.getApiService().getAllMovies().execute()
        assertEquals(1, actualResponse.body()?.page)
    }

    @Test
    fun getMovies() {
        val dummyResponseMovies = DataDummy.generatePlayingMovies()
        val movies = MutableLiveData<ArrayList<ResultsItemMovies?>>()
        movies.value = dummyResponseMovies

        `when`(moviesTvRepository.getAllMovies()).thenReturn(movies)
        val moviesValue = viewModel.getPlayingMovies().value
        verify(moviesTvRepository).getAllMovies()

        assertNotNull(moviesValue)
        if (moviesValue != null) {
            assertEquals(20, moviesValue.size)
        }

        viewModel.getPlayingMovies().observeForever(observer)
        verify(observer).onChanged(dummyResponseMovies)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}