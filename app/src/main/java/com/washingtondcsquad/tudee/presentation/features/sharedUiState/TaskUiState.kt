package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import java.time.LocalDate

data class TaskUiState(
    val taskId: String = "",
    val taskDate: LocalDate? = null,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskPriority: Priority? = null,
    val selectedCategory: Category? = null,
    val errorMessage: String? = null
)




