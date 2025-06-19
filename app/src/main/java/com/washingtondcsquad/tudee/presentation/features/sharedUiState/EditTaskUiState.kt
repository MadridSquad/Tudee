package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import java.time.LocalDate

data class EditTaskUiState(
    val taskId: Int = 0,
    val taskTitle: String  = "",
    val taskDescription: String = "",
    val taskDate: String = "",
    val selectedPriority: Priority?= null,
    val selectedCategory: Category ?= null,


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
