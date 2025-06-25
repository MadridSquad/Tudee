package com.washingtondcsquad.tudee.presentation.features.home

import com.washingtondcsquad.tudee.data.localSource.model.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TudeeStatus
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class HomeUiState(
    val isDarkMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val inProgressTasks: List<TaskUiState> = emptyList<TaskUiState>(),
    val todoTasks: List<TaskUiState> = emptyList<TaskUiState>(),
    val doneTasks: List<TaskUiState> = emptyList(),
    val tudeeStatus: TudeeStatus = TudeeStatus.ZERO_TASK,
    val isDarkTheme: Boolean = false,
    val isShowEditTaskBottomSheet : Boolean = false,
    val isShowTaskDetailsBottomSheet: Boolean = false

)

fun List<Task>.toUiState(): List<TaskUiState> = this.map { it.toTaskUiState() }

fun Task.toTaskUiState(): TaskUiState = TaskUiState(
    taskId = id,
    taskTitle = title,
    taskDescription = description,
    taskPriority = priority,
    taskDate = date.toString(),
)
fun TaskUiState.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = this.taskId,
        categoryId = CategoryID(1),
        title = this.taskTitle,
        description = this.taskDescription,
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString(),
        status = TaskStatus.TODO.name ,
        priority = this.taskPriority.name
    )
}
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