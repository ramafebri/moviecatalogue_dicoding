package com.example.moviecatalogue.ui.tvshows

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowsViewModelTest {
    private lateinit var viewModel: TvShowsViewModel
    @Before
    fun setUp() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getTvShows() {
        val tvEntities = viewModel.getTvShows()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities.size)
    }
}