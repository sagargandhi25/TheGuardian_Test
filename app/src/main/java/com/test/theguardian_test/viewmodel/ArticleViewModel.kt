package com.test.theguardian_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.theguardian_test.repository.ArticleRepository
import com.test.theguardian_test.utils.LoadingState
import kotlinx.coroutines.*
import java.lang.Exception

class ArticleViewModel(private val articleRepository: ArticleRepository): ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
    get() = _loadingState

    /**
     *This is the job for all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val articleResults = articleRepository.results

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshFromRepository()
    }

    /**
     *Refresh data from repository. Use a coroutine launch to run in a background thread.
     */
    private fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                articleRepository.refreshArticle()
                _loadingState.value = LoadingState.LOADED
            }
            catch (networkError: Exception) {
                _loadingState.value = LoadingState.error(networkError.message)
            }
        }
    }

    /**
     *Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}