package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import java.util.UUID

data class TaskUiState(
    val taskId: UUID = UUID.randomUUID(),
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: String = "",
    val categoryImage: String = "",
)
