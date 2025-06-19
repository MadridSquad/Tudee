package com.washingtondcsquad.tudee.presentation.screens.tasksScreen

import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.utils.buildMonthDaysList
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.utils.getYearAndMonthTitleFromYearMonth
import com.washingtondcsquad.tudee.presentation.screens.tasksScreen.utils.todayInMillis
import org.koin.mp.KoinPlatform.getKoin
import java.time.Instant
import java.time.YearMonth
import java.time.ZoneId
import java.util.UUID

class TasksViewModel(
    private val tasksService: TasksService ,
    private val categoriesService: CategoriesService
) : BaseViewModel<TasksUiState>(
    TasksUiState(
        showDateDialog = false,
        selectedDateInMillis = todayInMillis(),
        currentMonth = YearMonth.now(),
        yearAndMonthTitle = getYearAndMonthTitleFromYearMonth(YearMonth.now()),
        monthDaysList = buildMonthDaysList(YearMonth.now(), System.currentTimeMillis()),
        fullSelectedDate = "",
        isFilterEnabled = false,
        tasksList = emptyList()

    )
) {
    init {
        loadData()
    }

    fun deleteTask(taskId: UUID) = tryToExecute(
        request = {
            tasksService.deleteTask(taskId)
        },
        onSuccess = {
            loadData()
        },
        onError = {
            onError(it)
        }
    )


    fun loadData() = tryToExecute(
        request = {
            val tasks = tasksService.getAllTasks()
            val tasksForUiState = tasks.map {
                TaskUiState(
                    taskId = it.id.toString(),
                    taskDate = it.date.toString(),
                    taskTitle = it.title,
                    taskDescription = it.description,
                    taskPriority = it.priority,
                    taskStatus = it.status,
                    categoryImage = categoriesService.getCategoryById(
                        it.categoryId.toString().toLong()
                    ).icon
                )
            }
            updateState {
                copy(tasksList = tasksForUiState)
            }
        },
        onSuccess = ::onSuccess,
        onError = ::onError
    )

    private fun onSuccess(response: Unit) {

    }

    private fun onError(error: Throwable) {

    }

    fun onDateSelectedFromPicker(millis: Long) {
        val selectedDate = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val currentMonth = YearMonth.of(selectedDate.year, selectedDate.month)
        val isSameDay = state.value.selectedDateInMillis == millis

        updateState {
            copy(
                selectedDateInMillis = millis,
                currentMonth = currentMonth,
                yearAndMonthTitle = getYearAndMonthTitleFromYearMonth(currentMonth),
                monthDaysList = buildMonthDaysList(currentMonth, millis),
                isFilterEnabled = if (isSameDay) !state.value.isFilterEnabled else true

            )
        }
    }

    fun onDaySelectedFromLazyRow(dayNumber: Int) {
        val currentMonth = state.value.currentMonth
        val selectedDate = currentMonth.atDay(dayNumber)
        val millis = selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val isSameDay = state.value.selectedDateInMillis == millis
        val isFilterEnabled = if (isSameDay) !state.value.isFilterEnabled else true

        val updatedMonthDaysList = buildMonthDaysList(currentMonth, millis).map {
            it.copy(
                isSelected = isFilterEnabled && it.dayNumber == dayNumber
            )
        }
        updateState {
            copy(
                selectedDateInMillis = millis,
                monthDaysList = updatedMonthDaysList,
                isFilterEnabled = isFilterEnabled
            )
        }
    }

    fun formatedSelectedDate(millis: Long): String {
        val date = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        return "${date.year}-${date.monthValue}-${date.dayOfMonth}"
    }

    fun setShowDialog(show: Boolean) {
        updateState {
            copy(
                showDateDialog = show
            )
        }
    }

    fun goToPreviousMonth() {

        val newMonth = state.value.currentMonth.minusMonths(1)
        updateState {
            copy(
                currentMonth = newMonth,
                yearAndMonthTitle = getYearAndMonthTitleFromYearMonth(newMonth),
                monthDaysList = buildMonthDaysList(newMonth, selectedDateInMillis).map {
                    it.copy(
                        isSelected = false
                    )
                }
            )
        }
    }

    fun goToNextMonth() {
        val newMonth = state.value.currentMonth.plusMonths(1)
        updateState {
            copy(
                currentMonth = newMonth,
                yearAndMonthTitle = getYearAndMonthTitleFromYearMonth(newMonth),
                monthDaysList = buildMonthDaysList(newMonth, selectedDateInMillis).map {
                    it.copy(
                        isSelected = false
                    )
                }
            )
        }
    }

    fun clearDatePicker(): Long {
        return todayInMillis()
    }


}




