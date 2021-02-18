package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.MoviesEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.data.source.remote.response.MoviesDetailResponse
import com.example.moviecatalogue.data.source.remote.response.TvDetailResponse
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.ui.utils.MockResponseFileReader
import com.example.moviecatalogue.utils.DataDummy
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

class DetailViewModelTest {
    private val movieId = 464052
    private val tvId = 69050

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var moviesTvRepository: MoviesTvRepository

    @Mock
    private lateinit var observerMovies: Observer<MoviesDetailResponse?>

    @Mock
    private lateinit var observerTv: Observer<TvDetailResponse?>

    @Mock
    private lateinit var observerFavTv: Observer<TvShowsEntity?>

    @Mock
    private lateinit var observerFavMovies: Observer<MoviesEntity?>

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
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

        `when`(moviesTvRepository.getDetailMovies()).thenReturn(movies)
        val detailMoviesValue = viewModel.getDetailMovies().value
        verify(moviesTvRepository).getDetailMovies()

        assertNotNull(detailMoviesValue)
        if (detailMoviesValue != null) {
            assertEquals(3, detailMoviesValue.genres?.size)
        }

        viewModel.getDetailMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyDetailMovies)
    }

    @Test
    fun getDetailTv() {
        val dummyDetailTv = DataDummy.generateDetailTv()
        val tv = MutableLiveData<TvDetailResponse?>()
        tv.value = dummyDetailTv

        `when`(moviesTvRepository.getDetailTv()).thenReturn(tv)
        val detailTvValue = viewModel.getDetailTv().value
        verify(moviesTvRepository).getDetailTv()

        assertNotNull(detailTvValue)
        if (detailTvValue != null) {
            assertEquals(3, detailTvValue.genres?.size)
        }

        viewModel.getDetailTv().observeForever(observerTv)
        verify(observerTv).onChanged(dummyDetailTv)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByIdFavMovies(){
        val movies = DataDummy.generateSingleFavMovies()
        viewModel.insertFavMovies(movies)
        verify(moviesTvRepository).insertFavMovies(movies)

        val favMoviesData = MutableLiveData<MoviesEntity>()
        favMoviesData.value = movies
        `when`(moviesTvRepository.getFavMoviesById(movieId)).thenReturn(favMoviesData)

        val favMoviesEntities = viewModel.getFavMoviesById(movieId).value
        verify(moviesTvRepository).getFavMoviesById(movieId)

        assertNotNull(favMoviesEntities)
        if (favMoviesEntities != null) {
            assertEquals(movies.id, favMoviesEntities.id)
        }

        viewModel.getFavMoviesById(movieId).observeForever(observerFavMovies)
        verify(observerFavMovies).onChanged(movies)
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

        val favMoviesEntities = viewModel.getFavMoviesById(movieId).value
        verify(moviesTvRepository).getFavMoviesById(movieId)

        assertNull(favMoviesEntities)
        assertEquals(favMoviesData.value, favMoviesEntities)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetByIdFavTv(){
        val tv = DataDummy.generateSingleFavTv()
        viewModel.insertFavTv(tv)
        verify(moviesTvRepository).insertFavTv(tv)

        val favTvData = MutableLiveData<TvShowsEntity>()
        favTvData.value = tv
        `when`(moviesTvRepository.getFavTvById(tvId)).thenReturn(favTvData)

        val favTvEntities = viewModel.getFavTvById(tvId).value
        verify(moviesTvRepository).getFavTvById(tvId)

        assertNotNull(favTvEntities)
        if (favTvEntities != null) {
            assertEquals(tv.id, favTvEntities.id)
        }

        viewModel.getFavTvById(tvId).observeForever(observerFavTv)
        verify(observerFavTv).onChanged(tv)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAndGetByIdFavTv(){
        val tv = DataDummy.generateSingleFavTv()
        viewModel.insertFavTv(tv)
        verify(moviesTvRepository).insertFavTv(tv)

        viewModel.delFavTv(tv)
        verify(moviesTvRepository).deleteFavTv(tv)

        val favTvData = MutableLiveData<TvShowsEntity?>()
        favTvData.value = null
        `when`(moviesTvRepository.getFavTvById(tvId)).thenReturn(favTvData)

        val favTvEntities = viewModel.getFavTvById(tvId).value
        verify(moviesTvRepository).getFavTvById(tvId)

        assertNull(favTvEntities)
        assertEquals(favTvData.value, favTvEntities)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}