package com.washingtondcsquad.tudee.presentation.features.sharedUiState

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority

data class AddTaskUiState(
    val taskId: String = "",
    val taskDate: String  ,
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
