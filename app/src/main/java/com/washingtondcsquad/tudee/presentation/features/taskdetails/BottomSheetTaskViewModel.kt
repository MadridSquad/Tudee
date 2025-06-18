package com.washingtondcsquad.tudee.presentation.features.taskdetails

import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.utils.next
import java.util.UUID

class BottomSheetTaskViewModel(
    private val tasksService: TasksService
) : BaseViewModel<TaskState>(TaskState.Loading) {

    private lateinit var currentTaskId: UUID

    fun loadTask(taskId: UUID) {
        currentTaskId = taskId
        tryToExecute(
            request = { tasksService.getTaskById(taskId) },
            onSuccess = { task ->
                updateState { TaskState.Loaded(task) }
            },
            onError = { error ->
                updateState {
                    TaskState.Error(
                        message = error.message ?: "Failed to load task",
                        retryAction = { loadTask(currentTaskId) }
                    )
                }
            }
        )
    }

    fun moveToNextStatus() {
        currentTaskId.let { taskId ->
            tryToExecute(
                request = {
                    val task = tasksService.getTaskById(taskId)
                    val updatedTask = task.copy(status = task.status.next())
                    tasksService.editTask(updatedTask)
                    updatedTask
                },
                onSuccess = { task ->
                    updateState { TaskState.Loaded(task) }
                },
                onError = { error ->
                    updateState {
                        TaskState.Error(
                            message = error.message ?: "Failed to update status",
                            retryAction = { moveToNextStatus() }
                        )
                    }
                }
            )
        }
    }
}
