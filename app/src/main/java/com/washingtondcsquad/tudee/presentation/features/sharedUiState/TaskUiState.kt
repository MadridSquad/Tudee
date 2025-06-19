package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Priority

data class TaskUiState(
    val taskId: Int = 0,
    val taskDate: String? = null,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: Priority = Priority.LOW,
    val taskStatus: String = "",
    val categoryImage: String = "",
)
