package com.washingtondcsquad.tudee.presentation.features.home

import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
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

fun List<Task>.toUiState(): List<TaskUiState> = this.map { it.toTaskUiState() }

fun Task.toTaskUiState(): TaskUiState = TaskUiState(
    taskId = id,
    taskTitle = title,
    taskDescription = description,
    taskPriority = priority,
    taskDate = date.toString(),
)

val dummyTasks = listOf(
    TaskUiState(
        taskId = TaskID(1),
        taskTitle = "Task 1",
        taskDescription = "Description 1",
        taskPriority = Priority.HIGH
    ), TaskUiState(
        taskId = TaskID(2),
        taskTitle = "Task 2",
        taskDescription = "Description 2",
        taskPriority = Priority.MEDIUM
    ), TaskUiState(
        taskId = TaskID(1),
        taskTitle = "Task 1",
        taskDescription = "Description 1",
        taskPriority = Priority.HIGH
    ), TaskUiState(
        taskId = TaskID(2),
        taskTitle = "Task 2",
        taskDescription = "Description 2",
        taskPriority = Priority.MEDIUM
    ), TaskUiState(
        taskId = TaskID(3),
        taskTitle = "Task 3",
        taskDescription = "Description 3",
        taskPriority = Priority.LOW
    ), TaskUiState(
        taskId = TaskID(4),
        taskTitle = "Task 4",
        taskDescription = "Description 4",
        taskPriority = Priority.LOW
    )
)