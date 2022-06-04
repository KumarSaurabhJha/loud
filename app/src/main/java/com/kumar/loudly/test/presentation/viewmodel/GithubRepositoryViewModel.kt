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

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable = throwable)
    }

    private val _noApi = MutableLiveData<Event<Boolean>>()
    val noApi: LiveData<Event<Boolean>> get() = _noApi

    private val _repoList = MutableLiveData<Event<List<GithubRepositoryDomainModel>>>()
    val repoList: LiveData<Event<List<GithubRepositoryDomainModel>>> get() = _repoList

    private fun fetchData(apiName: String) {
        cancelFetchDataJob()

        fetchDataJob = viewModelScope.launch(errorHandler) {

            val list = getGithubRepoUsecase.execute(apiName)
            when (list.isEmpty()) {
                true -> _noApi.postValue(Event(true))
                else -> _repoList.postValue(Event(list))
            }
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
        _noApi.postValue(Event(true))
    }

    fun onSearchApi(apiName: String?) {
        apiName?.let {
            when (it.isNotBlank()) {
                true -> fetchData(it)
                else -> _noApi.postValue(Event(true))
            }
        }
    }

}