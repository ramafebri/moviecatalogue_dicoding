package com.example.moviecatalogue

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogue.utils.DataDummy
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Test
import org.junit.Rule

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
        onView(withId(R.id.img_detail)).check(matches(DrawableMatcher(dummyMovies[1].imagePath)))
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
    }

    private fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)?.toBitmap()

            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }

}