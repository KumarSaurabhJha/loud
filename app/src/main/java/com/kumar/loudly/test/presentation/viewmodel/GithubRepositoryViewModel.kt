package com.kumar.loudly.test.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.free.now.test.presentation.util.Event
import com.kumar.loudly.test.domain.model.GithubRepositoryDomainModel
import com.kumar.loudly.test.domain.usecases.GetGithubRepoUsecase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GithubRepositoryViewModel(
    private val getGithubRepoUsecase: GetGithubRepoUsecase
) : ViewModel() {

    private var fetchDataJob: Job? = null
    private val apiName: String = "tetris"

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable = throwable)
    }
    private val _error = MutableLiveData<Event<Boolean>>()
    val error: LiveData<Event<Boolean>> get() = _error

    private val _repoList = MutableLiveData<Event<List<GithubRepositoryDomainModel>>>()
    val repoList: LiveData<Event<List<GithubRepositoryDomainModel>>> get() = _repoList

    fun init() {
        cancelFetchDataJob()

        fetchDataJob = viewModelScope.launch(errorHandler) {
            _repoList.postValue(Event(getGithubRepoUsecase.execute(apiName)))
        }

    }

    private fun cancelFetchDataJob() {
        fetchDataJob?.let {
            if (it.isActive)
                it.cancel()
        }
    }

    private fun onError(throwable: Throwable) {
        Log.e("RestError", "Error is: $throwable")
        _error.postValue(Event(true))
    }

    fun onRepoClick(githubRepositoryDomainModel: GithubRepositoryDomainModel) {

    }

}