package com.washingtondcsquad.tudee.presentation.features.home

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tasksService: TasksService
) : BaseViewModel<HomeUiState>(HomeUiState()), HomeListener {

    init {
        loadData()
    }

    fun loadData() = viewModelScope.launch {
        updateState {
            copy(isLoading = false, error = null)
        }
        tryToExecute(
            request = {
                tasksService.getAllTasks()
            },
            onSuccess = ::onSuccess,
            onError = ::onError
        )
    }

    private fun onSuccess(response: List<Task>) {
        val inProgressTasks = response.filter { it.status == TaskStatus.IN_PROGRESS.name }
        val doneTasks = response.filter { it.status == TaskStatus.DONE.name }
        val toDoTasks = response.filter { it.status == TaskStatus.TODO.name }

        updateState {
            copy(
                isLoading = false, error = null,
                inProgressTasks = inProgressTasks.toUiState(),
                doneTasks = doneTasks.toUiState(),
                todoTasks = toDoTasks.toUiState(),
                tudeeStatus = calculateOverviewAnalytics()
            )
        }

    }

    private fun calculateOverviewAnalytics(): TudeeStatus {
        val state = state.value
        return when {
            state.doneTasks.size == 3 && state.todoTasks.size == 7 -> {
                TudeeStatus.STAY_WORKING
            }

            state.doneTasks.isNotEmpty() && state.todoTasks.isEmpty() && state.inProgressTasks.isEmpty() -> {
                TudeeStatus.DOING_AMAZING
            }

            state.doneTasks.isEmpty() && state.todoTasks.isNotEmpty() && state.inProgressTasks.isEmpty() -> {
                TudeeStatus.ZERO_PROGRESS
            }

            state.inProgressTasks.isEmpty() && state.todoTasks.isEmpty() -> {
                TudeeStatus.ZERO_TASK
            }

            else -> {
                TudeeStatus.STAY_WORKING
            }

        }
    }

    private fun onError(error: Throwable) {
        updateState {
            copy(isLoading = false, error = error.message)
        }
    }

    override fun onTaskClicked(taskId: Int) {

    }

    override fun onThemeSwitched(isDarkMode: Boolean) {

    }
}