package com.washingtondcsquad.tudee.presentation.features.add_task

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.provider.StringProvider
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarController
import com.washingtondcsquad.tudee.presentation.components.snack_bar.SnackbarEvent
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.AddTaskUiState
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AddTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    private val stringProvider: StringProvider,
) : BaseViewModel<AddTaskUiState>(
    AddTaskUiState()
) {


    init {
        getAllCategories()
    }


    private fun getAllCategories() {
        tryToCollect(
            request = { categoryService.getAllCategories() },
            onChange = {
                updateState {
                    copy(categoryList = it)
                }
            },
            onError = { exception -> }
        )
    }

    fun updateDate(
        taskDate: kotlinx.datetime.LocalDate,
    ) {
        updateState {
            copy(
                taskDate = taskDate
            )
        }
    }

    fun onTitleChange(newTitle: String) {
        updateState {
            copy(
                taskTitle = newTitle
            )
        }
        updateButtonEnable()
    }

    fun onDescriptionChange(newDescription: String) {
        updateState {
            copy(
                taskDescription = newDescription
            )
        }
    }

    fun onShowDatePicker() {
        updateState {
            copy(isDatePickerDisplayed = true)
        }
    }

    fun onHideDatePicker() {
        updateState {
            copy(
                isDatePickerDisplayed = false
            )
        }
    }

    fun onDateSelected(dateAsMilliseconds: Long) {
        val realDate = Instant.fromEpochMilliseconds(dateAsMilliseconds)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
        updateState {
            copy(
                taskDate = realDate
            )
        }

    }

    fun onPrioritySelected(priority: Priority) {
        val currentPriority = state.value.selectedPriority
        if (currentPriority?.name != priority.name) {
            updateState {
                copy(
                    selectedPriority = priority
                )
            }
            updateButtonEnable()
        }
    }

    fun onCategorySelected(category: Category) {
        val currentCategory = state.value.selectedCategory
        if (currentCategory != category) {
            updateState {
                copy(
                    selectedCategory = category
                )
            }
            updateButtonEnable()
        }
    }

    fun updateButtonEnable() {
        updateState {
            copy(
                isButtonActionEnable =
                    state.value.taskTitle.isNotEmpty() &&
                            state.value.taskTitle.isNotBlank() &&
                            state.value.selectedPriority != null &&
                            state.value.selectedCategory != null
            )
        }
    }


    fun onClickAdd(
        onSuccess: (message: String) -> Unit,
        onError: (message: String) -> Unit,
        taskStatus: TaskStatus
    ) {
        Log.e("MY_TAG",taskStatus.toString())
        tryToExecute(
            request = {
                tasksService.createTask(
                    Task(
                        title = state.value.taskTitle,
                        description = state.value.taskDescription,
                        date = state.value.taskDate,
                        priority = state.value.selectedPriority!!,
                        id = TaskID(0),
                        categoryId = state.value.selectedCategory!!.id  ,
                        status = taskStatus
                    )
                )
            },
            onSuccess = {
                onSuccess(stringProvider.getString(R.string.add_task_successfully))
                clearDate()
                viewModelScope.launch {
                    SnackbarController.sendEvent(event= SnackbarEvent(message = "Task added successfully"))
                }
            },
            onError = { exception ->
                onError(stringProvider.getString(R.string.some_error_happened))
            }
        )
    }

    private fun clearDate() {
        updateState {
            copy(
                taskTitle = "",
                taskDescription = "",
                selectedCategory = null,
                selectedPriority = null,
                isButtonActionEnable = false
            )
        }
    }
}