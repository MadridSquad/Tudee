package com.washingtondcsquad.tudee.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE>(initialValue: STATE) : ViewModel() {

    protected val _uiState: MutableStateFlow<STATE> = MutableStateFlow(initialValue)
    val state = _uiState.asStateFlow()

    fun <T> tryToExecute(
        request: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                request().also(onSuccess)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun <T> tryToCollect(
        request: suspend () -> Flow<T>,
        onChange: (T) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        viewModelScope.launch {
            request.invoke()
                .catch { exception ->
                    onError(exception)
                }
                .collect { newValue -> onChange(newValue) }
        }
    }

    protected fun updateState(reducer: STATE.() -> STATE) = _uiState.update(reducer)
}