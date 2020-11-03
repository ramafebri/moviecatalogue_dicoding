package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.utils.DataDummy
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovies = DataDummy.generateMovies()
    private val dummyTvShows = DataDummy.generateTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule (MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_home_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_home_fragment)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.navigation_tvshows)).perform(click())
        onView(withId(R.id.rv_tvShows_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows_fragment)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_home_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name)).check(matches(withText(dummyMovies[1].title)))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(withText(dummyMovies[1].year)))

        onView(withId(R.id.tv_detail_motto)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_motto)).check(matches(withText(dummyMovies[1].motto)))
        onView(withId(R.id.tv_detail_director)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_director)).check(matches(withText(dummyMovies[1].director)))

        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(withText(dummyMovies[1].description)))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withTagValue(equalTo(dummyMovies[1].imagePath)))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.navigation_tvshows)).perform(click())
        onView(withId(R.id.rv_tvShows_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name)).check(matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(withText(dummyTvShows[0].year)))

        onView(withId(R.id.tv_detail_motto)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_motto)).check(matches(withText(dummyTvShows[0].motto)))
        onView(withId(R.id.tv_detail_director)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_director)).check(matches(withText(dummyTvShows[0].director)))

        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(withText(dummyTvShows[0].description)))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withTagValue(equalTo(dummyTvShows[0].imagePath)))
    }
}