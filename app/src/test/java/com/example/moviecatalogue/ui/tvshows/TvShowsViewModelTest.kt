package com.example.moviecatalogue.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.remote.response.ResultsItemTv
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
class TvShowsViewModelTest {
    private lateinit var viewModel: TvShowsViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesTvRepository: MoviesTvRepository

    @Mock
    private lateinit var observer: Observer<ArrayList<ResultsItemTv?>>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowsViewModel(moviesTvRepository)
        viewModel.setTvShows()

        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `get popular tv and check response Code 200 returned`(){
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("response_tv.json").content)
        mockWebServer.enqueue(response)

        val  actualResponse = ApiConfig.getApiService().getAllTv().execute()
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

    @Test
    fun `get popular tv and check pages size`(){
        val  actualResponse = ApiConfig.getApiService().getAllTv().execute()
        assertEquals(1, actualResponse.body()?.page)
    }

    @Test
    fun getTvShows() {
        val dummyResponseTv = DataDummy.generatePopularTv()
        val tv = MutableLiveData<ArrayList<ResultsItemTv?>>()
        tv.value = dummyResponseTv

        `when`(moviesTvRepository.getAllTv()).thenReturn(tv)
        val tvValue = viewModel.getTvShows().value
        verify(moviesTvRepository).getAllTv()

        assertNotNull(tvValue)
        if (tvValue != null) {
            assertEquals(20, tvValue.size)
        }

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyResponseTv)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}