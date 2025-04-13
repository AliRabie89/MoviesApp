

package com.ali.moviesApp.main.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.moviesApp.main.common.Constants
import com.ali.moviesApp.main.data.remote.dtos.NetworkState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val TAG = BaseViewModel::class.simpleName
    private var parentJob: Job? = null
    fun customCoroutineExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->

        }
    }

    /// un auth sharedFlow
    private val _unAuthorizedFlow = MutableSharedFlow<Boolean>()
    internal val unAuthorizedFlow get() = _unAuthorizedFlow.asSharedFlow()

    // maintenance sharedFlow
    private val _maintenanceFlow = MutableSharedFlow<Boolean>()
    internal val maintenanceFlow = _maintenanceFlow.asSharedFlow()

    /// update sharedFlow
    private val _updateFlow = MutableSharedFlow<Boolean>()
    internal val updateFlow = _updateFlow.asSharedFlow()

    // network connection flow
    private val _connectionErrorFlow = MutableSharedFlow<Boolean>()
    internal val connectionErrorFlow = _connectionErrorFlow.asSharedFlow()


    protected fun <T> executeSharedFlow(
        sharedFlow: MutableSharedFlow<NetworkState>,
        request: Flow<T>,
        onCollectSuccess: ((T) -> Unit)? = null
    ) {
        parentJob = viewModelScope.launch(Dispatchers.IO + handlerSharedException(sharedFlow)) {
            request.onStart {
                Log.e(TAG, "executeSharedFlow: onStart ")
                sharedFlow.emit(NetworkState.Loading)
            }.onCompletion {
                Log.e(TAG, "executeSharedFlow: onCompletion ${it?.message}")
                sharedFlow.emit(NetworkState.StopLoading)
            }.catch {
                it.printStackTrace()
                sharedFlow.emit(NetworkState.Error(it))
            }.collectLatest {
                Log.e(TAG, "executeSharedFlow: collectlatest ${it}")
                if (onCollectSuccess != null)
                    onCollectSuccess(it)
                sharedFlow.emit(NetworkState.Success(it))
            }
        }
    }

    private fun handlerSharedException(state: MutableSharedFlow<NetworkState>): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace()
            when (throwable.message) {
                Constants.ErrorApi.UNAUTHORIZED -> {
                    _unAuthorizedFlow.tryEmit(true)
                }

                Constants.ErrorApi.CONNECTION_ERROR -> {
                    _connectionErrorFlow.tryEmit(true)
                }
                else -> {
                    state.tryEmit(NetworkState.Error(throwable))
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        parentJob?.cancel()
    }
}