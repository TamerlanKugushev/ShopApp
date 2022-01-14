package com.example.shopapp.domain.util

import com.example.shopapp.data.model.ILocalData

sealed class Resource<out T: ILocalData> {
    data class Success<out T: ILocalData>(val data: T) : Resource<T>()
    class Loading<out T: ILocalData> : Resource<T>()
    data class Error<out T: ILocalData>(val error: Throwable) : Resource<T>()
}