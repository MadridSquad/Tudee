package com.washingtondcsquad.tudee.presentation.features.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class HomeViewModel(
    private val tasksService: TasksService, private val appPreferences: AppPreferencesService

) : BaseViewModel<HomeUiState>(HomeUiState()), HomeListener {

    init {
        loadData()
    }

    fun refresh() {
        updateState {
            copy(isLoading = true)
        }
        loadData()
        updateState {
            copy(isLoading = false)
        }
    }


    fun toggleTheme(isDark: Boolean) {
        tryToExecute(request = {
            appPreferences.setDarkModeEnabled(isDark)
            isDark
        }, onSuccess = {
            updateState {
                copy(isDarkTheme = isDark)
            }
        }, onError = {
            updateState {
                copy(error = it.message)
            }
        })

    }

    private fun loadData() = viewModelScope.launch {
        updateState {
            copy(isLoading = true, error = null)
        }
        var tasks: List<Task> = emptyList()

        tryToExecute(
            request = {
                tasksService.getAllTasks().collect {
                    onSuccess(it)
                    Log.i("refresh", "all data ${it}")
                }
                tasks

            }, onSuccess = ::onSuccess, onError = ::onError
        )
    }

    private fun onSuccess(response: List<Task>) {
        val inProgressTasks = response.filter { it.status == TaskStatus.IN_PROGRESS }
        val doneTasks = response.filter { it.status == TaskStatus.DONE }
        val toDoTasks = response.filter { it.status == TaskStatus.TODO }

        updateState {
            copy(
                isLoading = false,
                error = null,
                inProgressTasks = inProgressTasks.toUiState(),
                doneTasks = doneTasks.toUiState(),
                todoTasks = toDoTasks.toUiState(),
                tudeeStatus = calculateOverviewAnalytics(
                    inProgressCount = inProgressTasks.size,
                    todoCount = toDoTasks.size,
                    doneCount = doneTasks.size
                )
            )
        }

    }

    private fun calculateOverviewAnalytics(
        inProgressCount: Int, todoCount: Int, doneCount: Int
    ): TudeeStatus {
        val totalCount = inProgressCount + todoCount + doneCount
        return when {
            totalCount == 0 -> {
                TudeeStatus.ZERO_TASK
            }

            doneCount != 0 && doneCount <= totalCount / 2 -> {
                TudeeStatus.STAY_WORKING
            }

            doneCount >= totalCount / 2 -> {
                TudeeStatus.DOING_AMAZING
            }

            inProgressCount == 0 && todoCount == 0 -> {
                TudeeStatus.ZERO_PROGRESS
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
        toggleTheme(isDarkMode)
    }
}