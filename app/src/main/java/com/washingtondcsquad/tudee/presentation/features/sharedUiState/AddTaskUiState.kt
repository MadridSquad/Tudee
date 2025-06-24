package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class AddTaskUiState(
    val taskId: String = "",
    val taskDate: LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date,
    val taskTitle: String = "",
    val taskDescription: String = "",
    val errorMessage: String? = null,

    val categoryList: List<Category> = emptyList(),
    val selectedCategory: Category? = null,

    val priorityList: List<Priority> = listOf(
        Priority.HIGH,
        Priority.MEDIUM,
        Priority.LOW,
    ),
    val selectedPriority: Priority? = null,
    val isButtonActionEnable: Boolean = false,

    val isDatePickerDisplayed: Boolean = false,

    )
