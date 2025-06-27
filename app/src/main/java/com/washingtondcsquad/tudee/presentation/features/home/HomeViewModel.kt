package com.washingtondcsquad.tudee.presentation.features.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.AppPreferencesService
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    private val appPreferences: AppPreferencesService
) : BaseViewModel<HomeUiState>(HomeUiState()), HomeListener {

    init {
        loadThemeState()
        loadData()
    }


    fun loadThemeState() {
        updateState {
            copy(isLoading = true)
        }
        tryToExecute(
            request = {
                appPreferences.isDarkModeEnabled()

            }, onSuccess = {
                viewModelScope.launch {
                    it.collectLatest {
                        updateState {
                            copy(isDarkTheme = it, isLoading = false)
                        }
                    }
                }
            }, onError = ::onError
        )

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

    private fun loadData() {
        viewModelScope.launch {
            tasksService.getAllTasks().combine(categoryService.getAllCategories()){tasks,category->
                tasks to category
            }.collect {
                val tasks = it.first
                val categories = it.second
                Log.e("MY_TAG","this is task ")
                    Log.e("MY_TAG","this is catgro ")
                    val inProgresTasks = tasks.filter { it.status == TaskStatus.IN_PROGRESS }
                    val doneTasks = tasks.filter { it.status == TaskStatus.DONE }
                    val toDoTasks = tasks.filter { it.status == TaskStatus.TODO }
                    withContext(Dispatchers.Main) {

                        updateState {
                            copy(
                                inProgressTasks = inProgresTasks.map { progressTask ->
                                    TaskUiState(
                                        taskId = progressTask.id,
                                        taskDate = progressTask.date.toString(),
                                        taskTitle = progressTask.title,
                                        taskDescription = progressTask.description,
                                        taskPriority = progressTask.priority,
                                        taskStatus = progressTask.status.toString(),
                                        categoryImage = categories.find { it.id == progressTask.categoryId }?.iconPath
                                            ?: ImageSource.PredefinedDrawable(R.drawable.gym)
                                    )
                                },
                                todoTasks = toDoTasks.map { progressTask ->
                                    TaskUiState(
                                        taskId = progressTask.id,
                                        taskDate = progressTask.date.toString(),
                                        taskTitle = progressTask.title,
                                        taskDescription = progressTask.description,
                                        taskPriority = progressTask.priority,
                                        taskStatus = progressTask.status.toString(),
                                        categoryImage = categories.find { it.id == progressTask.categoryId }?.iconPath
                                            ?: ImageSource.PredefinedDrawable(R.drawable.gym)
                                    )
                                },
                                doneTasks = doneTasks.map { progressTask ->
                                    TaskUiState(
                                        taskId = progressTask.id,
                                        taskDate = progressTask.date.toString(),
                                        taskTitle = progressTask.title,
                                        taskDescription = progressTask.description,
                                        taskPriority = progressTask.priority,
                                        taskStatus = progressTask.status.toString(),
                                        categoryImage = categories.find { it.id == progressTask.categoryId }?.iconPath
                                            ?: ImageSource.PredefinedDrawable(R.drawable.gym)
                                    )
                                },
                                tudeeStatus = calculateOverviewAnalytics(
                                    inProgressCount =inProgresTasks.size ,
                                    todoCount = toDoTasks.size,
                                    doneCount = doneTasks.size,
                                )
                            )
                        }
                    }

            }
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

    override fun onTaskClicked(taskId: Int) {}

    override fun onThemeSwitched(isDarkMode: Boolean) {
        tryToExecute(request = {
            appPreferences.setDarkMode(isDarkMode)
        }, onSuccess = {
            updateState {
                copy(isDarkMode = isDarkMode)
            }
        }, onError = {
            updateState {
                copy(error = it.message)
            }
        })
    }
}