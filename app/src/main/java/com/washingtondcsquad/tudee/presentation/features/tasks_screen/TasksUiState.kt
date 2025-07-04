package com.washingtondcsquad.tudee.presentation.features.tasks_screen

import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.buildMonthDaysList
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.getYearAndMonthTitleFromYearMonth
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.todayInMillis
import java.time.YearMonth

data class TasksUiState(
    val showDateDialog: Boolean = false,
    val currentMonth: YearMonth = YearMonth.now(),
    val selectedDateInMillis: Long = todayInMillis(),
    val yearAndMonthTitle: String = getYearAndMonthTitleFromYearMonth(YearMonth.now()),
    val fullSelectedDate: String = "",
    val isFilterEnabled: Boolean = false,
    val monthDaysList: List<DayUiModel> = buildMonthDaysList(
        YearMonth.now(),
        System.currentTimeMillis()
    ),
    val tasksList: List<TaskUiState> = emptyList()
)

data class DayUiModel(
    val dayNumber: Int,
    val dayName: String,
    val isSelected: Boolean
)


