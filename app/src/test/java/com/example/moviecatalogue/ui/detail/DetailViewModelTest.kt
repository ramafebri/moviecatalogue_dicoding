package com.example.moviecatalogue.ui.detail

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val dummyMovies = DataDummy.generateMovies()[0]
    private val dummyTv = DataDummy.generateTvShows()[0]
    private val moviesId = dummyMovies.moviesId
    private val tvShowsId = dummyTv.tvShowsId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedDetail(moviesId, true)
        viewModel.setSelectedDetail(tvShowsId, false)
    }

    @Test
    fun getDetailMovies() {
        val moviesEntities = viewModel.getDetailMovies()
        assertNotNull(moviesEntities)
        if(moviesEntities !== null){
            assertEquals(dummyMovies.moviesId, moviesEntities.moviesId)
            assertEquals(dummyMovies.year, moviesEntities.year)
            assertEquals(dummyMovies.motto, moviesEntities.motto)
            assertEquals(dummyMovies.description, moviesEntities.description)
            assertEquals(dummyMovies.imagePath, moviesEntities.imagePath)
            assertEquals(dummyMovies.title, moviesEntities.title)
        }
    }

    @Test
    fun detailMoviesWithNull() {
        viewModel.setSelectedDetail("11", true)
        val moviesEntities = viewModel.getDetailMovies()
        assertNull("the object should be null",moviesEntities)
    }

    @Test
    fun detailTvWithNull() {
        viewModel.setSelectedDetail("11", false)
        val tvShowsEntity = viewModel.getDetailTvShows()
        assertNull("the object should be null",tvShowsEntity)
    }

    @Test
    fun getDetailTvShows() {
        val tvShowsEntity = viewModel.getDetailTvShows()
        assertNotNull(tvShowsEntity)
        if(tvShowsEntity !== null){
            assertEquals(dummyTv.tvShowsId, tvShowsEntity.tvShowsId)
            assertEquals(dummyTv.year, tvShowsEntity.year)
            assertEquals(dummyTv.motto, tvShowsEntity.motto)
            assertEquals(dummyTv.description, tvShowsEntity.description)
            assertEquals(dummyTv.imagePath, tvShowsEntity.imagePath)
            assertEquals(dummyTv.title, tvShowsEntity.title)
        }
    }
}