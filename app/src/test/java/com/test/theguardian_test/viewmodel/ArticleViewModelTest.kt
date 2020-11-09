package com.test.theguardian_test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.test.theguardian_test.MainCoroutineRule
import com.test.theguardian_test.repository.ArticleRepository
import com.test.theguardian_test.utils.LiveDataTestUtil
import com.test.theguardian_test.utils.LoadingState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ArticleViewModelTest {

    @Mock
    private lateinit var mrepo: ArticleRepository

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var articleViewModel: ArticleViewModel

    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test refresh data from repository do what it should do`() = runBlocking {

        articleViewModel = ArticleViewModel(mrepo)
        val liveDataTestUtil = LiveDataTestUtil<LoadingState>()

        //action
        //when ArticleViewModel is created refreshArticle is called.
        //verify
        Mockito.verify(mrepo).refreshArticle()
        val dataEmitted = liveDataTestUtil.getValue(articleViewModel.loadingState)
        assertEquals(LoadingState.Status.SUCCESS.name,dataEmitted?.status?.name)
    }

    @Test
    fun `test loading error is called`() = runBlocking {
        //prep
        //action
        //when articleViewModel is created refreshFromRepository is called.
        //verify
        Mockito.`when`(mrepo.refreshArticle()).thenThrow(RuntimeException())
        articleViewModel = ArticleViewModel(mrepo)
        val mediatorLiveData = MediatorLiveData<LoadingState>()
        mediatorLiveData.addSource(articleViewModel.loadingState) {
            when(it.status) {
                LoadingState.Status.RUNNING -> {
                    //ignore
                }
                else -> {
                    assertEquals(LoadingState.Status.FAILED.name,it?.status?.name)
                }
            }
        }
    }
}