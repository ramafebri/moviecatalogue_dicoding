package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovies = DataDummy.generatePlayingMovies()
    private val dummyTvShows = DataDummy.generatePopularTv()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

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
        onView(withId(R.id.rv_home_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name)).check(matches(withText(dummyMovies[0]?.originalTitle.toString())))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(withText(dummyMovies[0]?.releaseDate)))

        onView(withId(R.id.tv_detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_language)).check(matches(withText(dummyMovies[0]?.originalLanguage)))
        onView(withId(R.id.tv_detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_popularity)).check(matches(withText(dummyMovies[0]?.popularity.toString())))

        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(withText(dummyMovies[0]?.overview)))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withTagValue(equalTo(dummyMovies[0]?.posterPath)))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.navigation_tvshows)).perform(click())
        onView(withId(R.id.rv_tvShows_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name)).check(matches(withText(dummyTvShows[0]?.name)))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(withText(dummyTvShows[0]?.firstAirDate)))

        onView(withId(R.id.tv_detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_language)).check(matches(withText(dummyTvShows[0]?.originalLanguage)))
        onView(withId(R.id.tv_detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_popularity)).check(matches(withText(dummyTvShows[0]?.popularity.toString())))

        onView(withId(R.id.tv_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_overview)).check(matches(withText(dummyTvShows[0]?.overview)))
        onView(withId(R.id.img_detail)).check(matches(isDisplayed()))
        onView(withTagValue(equalTo(dummyTvShows[0]?.posterPath)))
    }

    @Test
    fun loadFavMovies() {
        onView(withId(R.id.rv_home_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(scrollTo()).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())

        onView(withId(R.id.rv_fav_movies_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movies_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavTv() {
        onView(withId(R.id.navigation_tvshows)).perform(click())
        onView(withId(R.id.rv_tvShows_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvShows_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(scrollTo()).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText(R.string.title_fav_tv_shows)).perform(click())

        onView(withId(R.id.rv_fav_tv_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add)).perform(scrollTo()).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}