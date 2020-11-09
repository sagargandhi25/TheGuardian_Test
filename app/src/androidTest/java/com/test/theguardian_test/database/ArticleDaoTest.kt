package com.test.theguardian_test.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.test.theguardian_test.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    @get:Rule
    var instantTaskExecutorRule =InstantTaskExecutorRule()

    private lateinit var database: ArticleDatabase

    private lateinit var dao: ArticleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ArticleDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.articleDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArticle() = runBlockingTest {
        val articleItem = DatabaseArticle("australia-news/2020/nov/09/","Australia’s state by state coronavirus lockdown rules",
        "What are the restrictions within Victoria and the border closures","https://media.guim.co.uk/d8001c1abe3d986a6e29df76729fe0a18d397b96/0_275_5005_3004/500.jpg")

        dao.insertAll(listOf(articleItem))

        val allArticle = dao.getLocalDBArticles().getOrAwaitValue()

        assertThat(allArticle).contains(articleItem)
    }

}