package com.example.shopapp.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopapp.domain.util.Resource
import com.example.shopapp.data.model.ILocalData
import kotlinx.coroutines.*

abstract class BaseViewModel<T : ILocalData> : ViewModel() {

    protected val _liveData = MutableLiveData<Resource<T>>()
    val liveData: LiveData<Resource<T>> = _liveData

    private val viewModelCoroutineScope =
        CoroutineScope(
            Dispatchers.IO
                    + SupervisorJob()
                    + CoroutineExceptionHandler { _, error ->
                _liveData.postValue(Resource.Error(error))
            }
        )

    fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    protected fun runAsync(block: suspend () -> Unit) {
        viewModelCoroutineScope.launch {
            block()
        }
    }

    protected fun showLoading() {
        _liveData.postValue(Resource.Loading())
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

}