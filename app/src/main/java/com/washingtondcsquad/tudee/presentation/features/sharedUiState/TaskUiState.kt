package com.washingtondcsquad.tudee.presentation.features.sharedUiState

data class TaskUiState(
    val taskId: String = "",
    val taskDate: String = "",
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: String = "",
    val taskStatus: String = "",
    val categoryImage: String = "",
)
