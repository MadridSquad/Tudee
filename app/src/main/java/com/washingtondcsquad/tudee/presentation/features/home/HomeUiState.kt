package com.washingtondcsquad.tudee.presentation.features.home

import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val inProgressTasks: List<TaskUiState> = dummyTasks,
    val todoTasks: List<TaskUiState> = dummyTasks,
    val doneTasks: List<TaskUiState> = emptyList(),
    val tudeeStatus: TudeeStatus = TudeeStatus.ZERO_TASK
)

fun List<Task>.toUiState(): List<TaskUiState> =
    this.map { it.toTaskUiState() }

fun Task.toTaskUiState(): TaskUiState =
    TaskUiState(
        taskId = id,
        taskTitle = title,
        taskDescription = description,
        taskPriority = priority,
        taskDate = date,
        categoryImage = categoryImage
    )

val dummyTasks = listOf(
    TaskUiState(
        taskId = 1,
        taskTitle = "Task 1",
        taskDescription = "Description 1",
        taskPriority = Priority.HIGH
    ),
    TaskUiState(
        taskId = 2,
        taskTitle = "Task 2",
        taskDescription = "Description 2",
        taskPriority = Priority.MEDIUM
    ), TaskUiState(
        taskId = 1,
        taskTitle = "Task 1",
        taskDescription = "Description 1",
        taskPriority = Priority.HIGH
    ),
    TaskUiState(
        taskId = 2,
        taskTitle = "Task 2",
        taskDescription = "Description 2",
        taskPriority = Priority.MEDIUM
    ),
    TaskUiState(
        taskId = 3,
        taskTitle = "Task 3",
        taskDescription = "Description 3",
        taskPriority = Priority.LOW
    ),
    TaskUiState(
        taskId = 4,
        taskTitle = "Task 4",
        taskDescription = "Description 4",
        taskPriority = Priority.LOW
    )
)