package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.TaskID

data class TaskUiState(
    val taskId: TaskID = TaskID(0L),
    val taskDate: String? = null,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: Priority = Priority.LOW,
    val taskStatus: String = "",
    val categoryImage : ImageSource
)
