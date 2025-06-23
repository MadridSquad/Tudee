package com.washingtondcsquad.tudee.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE>(initialValue: STATE) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialValue)
    val state = _state.asStateFlow()

    fun <T> tryToExecute(
        request: suspend () -> T, onSuccess: (T) -> Unit, onError: (Exception) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                request().also(onSuccess)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected fun updateState(reducer: STATE.() -> STATE) {
        _state.update(reducer)
        _state.value = _state.value.reducer()
    }
}