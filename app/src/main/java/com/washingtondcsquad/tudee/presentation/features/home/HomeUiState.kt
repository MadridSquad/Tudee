package com.washingtondcsquad.tudee.presentation.features.home

import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus

data class HomeUiState(
    val isDarkMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val inProgressTasks: List<TaskUiState> = emptyList<TaskUiState>(),
    val todoTasks: List<TaskUiState> = emptyList<TaskUiState>(),
    val doneTasks: List<TaskUiState> = emptyList(),
    val tudeeStatus: TudeeStatus = TudeeStatus.ZERO_TASK,
    val isDarkTheme: Boolean = false
)