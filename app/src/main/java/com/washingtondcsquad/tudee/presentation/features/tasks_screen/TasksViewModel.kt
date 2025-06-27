package com.washingtondcsquad.tudee.presentation.features.tasks_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.LocalDateParser
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.buildMonthDaysList
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.getYearAndMonthTitleFromYearMonth
import com.washingtondcsquad.tudee.presentation.features.tasks_screen.utils.todayInMillis
import kotlinx.coroutines.launch
import kotlinx.datetime.toJavaLocalDate
import java.time.Instant
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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
        observeTasks()
    }
    var snackBarMessage = mutableStateOf<String?>(null)

    fun deleteTask(taskId: TaskID) = tryToExecute(
        request = {
            val taskUi = state.value.tasksList.find { it.taskId == taskId }

            val task = taskUi?.taskDate?.let {
                Task(
                    id = taskUi.taskId,
                    title = taskUi.taskTitle,
                    description = taskUi.taskDescription,
                    priority = taskUi.taskPriority,
                    status = TaskStatus.valueOf(taskUi.taskStatus),
                    date = LocalDateParser(taskUi.taskDate),
                    categoryId = CategoryID(0L),
                )
            }

            if (task != null) {
                tasksService.deleteTask(task)
            }
        },
        onSuccess = {
            snackBarMessage.value = "Deleted task successfully."
            observeTasks()
        },
        onError = {
            onError(it)
        }
    )


    private fun observeTasks() {
       viewModelScope.launch {
            tasksService.getAllTasks().collect { tasks ->
                val tasksForUiState = tasks.map {
                    val category = categoriesService.getCategoryById(it.categoryId)
                    TaskUiState(
                        taskId = it.id,
                        taskDate = it.date.toJavaLocalDate()
                            .format(DateTimeFormatter.ofPattern("d-M-yyyy")),
                        taskTitle = it.title,
                        taskDescription = it.description,
                        taskPriority = it.priority,
                        taskStatus = it.status.toString(),
                        categoryImage = category.iconPath,
                    )
                }
                updateState {
                    copy(tasksList = tasksForUiState)
                }
            }
        }
    }
    private fun onSuccess(response: Unit) {

    }

    private fun onError(error: Throwable) {

    }

    fun onTaskClicked(taskId: Int) {

    }

    fun onDateSelectedFromPicker(millis: Long) {
        val selectedDate = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.of("UTC"))
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
        val millis = selectedDate.atStartOfDay(ZoneId.of("UTC")).toInstant().toEpochMilli()
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
        val dataInLocalDate = Instant.ofEpochMilli(millis)
            .atZone(ZoneId.of("UTC"))
            .toLocalDate()
        val realDate = dataInLocalDate.format(DateTimeFormatter.ofPattern("d-M-yyyy"))
        return realDate
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




