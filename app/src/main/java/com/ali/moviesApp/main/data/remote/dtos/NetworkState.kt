

package com.ali.moviesApp.main.data.remote.dtos
sealed class NetworkState {
    object Loading : NetworkState()
    object StopLoading : NetworkState()
    data class Success<T>(val data: T) : NetworkState()
    data class Error(val throwable: Throwable) : NetworkState()
}