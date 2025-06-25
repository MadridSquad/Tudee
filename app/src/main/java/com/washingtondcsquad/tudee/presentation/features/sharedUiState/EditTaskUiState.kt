package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.TaskID
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class EditTaskUiState(
    val taskId: TaskID,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val taskDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date,
    val selectedPriority: Priority? = null,
    val selectedCategory: Category? = null,
    val errorMessage: String? = null,
    val categoryList: List<Category> = emptyList(),
    val priorityList: List<Priority> = listOf(
        Priority.HIGH,
        Priority.MEDIUM,
        Priority.LOW,
    ),
    val isButtonActionEnable: Boolean = true,
    val isDatePickerDisplayed: Boolean = false,
)