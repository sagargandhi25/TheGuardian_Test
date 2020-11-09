package com.test.theguardian_test

import androidx.lifecycle.MediatorLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.test.theguardian_test.utils.LoadingState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Mock
    val fakeLoadingState= MediatorLiveData<LoadingState>()

    @Test
    fun test_mainActivityIsDisplayed() {
        emitLoadingState(LoadingState.LOADING)
        Espresso.onView(withId(R.id.main)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isFragmentArticleListVisible() {
        emitLoadingState(LoadingState.LOADING)
        Espresso.onView(withId(R.id.Articlelist_fragment_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isRecyclerviewVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(
                ViewAssertions.matches(
                        ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_isProgressBarNotShowingWhenSuccess() {
        emitLoadingState(LoadingState.LOADED)
        Espresso.onView(withId(R.id.progressBar)).check(
                ViewAssertions.matches(
                        ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    private fun emitLoadingState(loadingState: LoadingState){
        fakeLoadingState.postValue(loadingState)
    }

}