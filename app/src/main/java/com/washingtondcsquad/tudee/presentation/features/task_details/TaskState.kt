package com.washingtondcsquad.tudee.presentation.features.task_details

import com.washingtondcsquad.tudee.domain.entity.Task

sealed class TaskState {
    object Loading : TaskState()
    data class Loaded(val task: Task) : TaskState()
    data class Error(val message: String, val retryAction: () -> Unit) : TaskState()
}