package com.washingtondcsquad.tudee.presentation.screens.tasksScreen

import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import java.time.YearMonth

data class TasksUiState(
    val showDateDialog: Boolean,
    val currentMonth: YearMonth,
    val selectedDateInMillis: Long,
    val yearAndMonthTitle: String,
    val fullSelectedDate: String,
    val isFilterEnabled: Boolean,
    val monthDaysList: List<DayUiModel>,
    val tasksList: List<TaskUiState>
)

data class DayUiModel(
    val dayNumber: Int,
    val dayName: String,
    val isSelected: Boolean
)


