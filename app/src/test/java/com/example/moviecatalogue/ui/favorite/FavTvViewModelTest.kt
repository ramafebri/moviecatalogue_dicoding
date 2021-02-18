package com.example.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.MoviesTvRepository
import com.example.moviecatalogue.data.source.local.entity.TvShowsEntity
import com.example.moviecatalogue.ui.favorite.tv.FavoriteTvViewModel
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
class FavTvViewModelTest {
    private lateinit var viewModel: FavoriteTvViewModel
    private val tvId = 69050

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesTvRepository: MoviesTvRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowsEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowsEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteTvViewModel(moviesTvRepository)
    }

    @Test
    fun getFavTv() {
        val dummyFavoriteTv = pagedList
        `when`(dummyFavoriteTv.size).thenReturn(2)
        val tv = MutableLiveData<PagedList<TvShowsEntity>>()
        tv.value = dummyFavoriteTv

        `when`(moviesTvRepository.getFavTv()).thenReturn(tv)
        val tvValue = viewModel.getFavTv().value
        verify(moviesTvRepository).getFavTv()

        assertNotNull(tvValue)
        if (tvValue != null) {
            assertEquals(2, tvValue.size)
        }

        viewModel.getFavTv().observeForever(observer)
        verify(observer).onChanged(dummyFavoriteTv)
    }

    @Test
    fun insertAndGetByIdFavTv(){
        val tv = DataDummy.generateSingleFavTv()
        viewModel.insertFavTv(tv)
        verify(moviesTvRepository).insertFavTv(tv)

        val favTvData = MutableLiveData<TvShowsEntity>()
        favTvData.value = tv
        `when`(moviesTvRepository.getFavTvById(tvId)).thenReturn(favTvData)

        val favTvEntities = moviesTvRepository.getFavTvById(tvId).value
        verify(moviesTvRepository).getFavTvById(tvId)

        assertNotNull(favTvEntities)
        if (favTvEntities != null) {
            assertEquals(tv.id, favTvEntities.id)
        }
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

        val favTvEntities = moviesTvRepository.getFavTvById(tvId).value
        verify(moviesTvRepository).getFavTvById(tvId)

        assertNull(favTvEntities)
    }
}