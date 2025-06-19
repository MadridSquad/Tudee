package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Priority

data class TaskUiState(
    val taskId: String = "",
    val taskDate: String = "",
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: Priority = Priority.LOW,
    val categoryImage: String = "",
)
