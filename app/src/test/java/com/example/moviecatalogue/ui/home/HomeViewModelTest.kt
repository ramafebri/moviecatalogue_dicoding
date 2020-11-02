package com.example.moviecatalogue.ui.home

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    @Before
    fun setUp() {
        viewModel = HomeViewModel()
    }

    @Test
    fun getMovies() {
        val moviesEntities = viewModel.getMovies()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }
}