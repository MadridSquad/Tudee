package com.washingtondcsquad.tudee.presentation.features.add_task

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.AddTaskUiState
import com.washingtondcsquad.tudee.presentation.provider.StringProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class AddTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    private val stringProvider: StringProvider,
    taskDate: kotlinx.datetime.LocalDate,
) : BaseViewModel<AddTaskUiState>(
    AddTaskUiState(
        taskDate = taskDate.toString()
    )
) {


    init {
        getAllCategories()
    }


    private fun getAllCategories() {
        viewModelScope.launch {
            categoryService.getAllCategories().collect {
                withContext(Dispatchers.Main) {
                    updateState {
                        copy(categoryList = it)
                    }
                }
            }
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
        val dataInLocalDate = Instant.ofEpochMilli(dateAsMilliseconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        val realDate = dataInLocalDate.format(DateTimeFormatter.ofPattern("d-M-yyyy"))
        updateState {
            copy(
                taskDate = realDate
            )
        }

    }

    fun onPrioritySelected(priority: Priority) {
        val currentPriority = _state.value.selectedPriority
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
        val currentCategory = _state.value.selectedCategory
        if (currentCategory != category) {
            updateState {
                copy(
                    selectedCategory = category
                )
            }
            tryToExecute(
                request = {
                    categoryService.editCategory(category.copy(taskCount = 1))
                },
                onSuccess = {},
                onError = { exception ->

                }
            )
            updateButtonEnable()
        }
    }

    fun updateButtonEnable() {
        updateState {
            copy(
                isButtonActionEnable =
                    _state.value.taskTitle.isNotEmpty() &&
                            _state.value.taskTitle.isNotBlank() &&
                            _state.value.selectedPriority != null &&
                            _state.value.selectedCategory != null
            )
        }
    }


    fun onClickAdd(
        onSuccess: (message: String) -> Unit,
        onError: (message: String) -> Unit,
    ) {
        tryToExecute(
            request = {
//                tasksService.createTask(
//
//                )
            },
            onSuccess = {
                onSuccess(stringProvider.getString(R.string.add_task_successfully))
                clearDate()
            },
            onError = { exception ->
                onError(stringProvider.getString(R.string.some_error_happened))
            }
        )
    }

    private fun clearDate() {
        updateState {
            copy(
                taskId = 0.toString(),
                taskTitle = "",
                taskDescription = "",
                taskDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d-M-yyyy")),
                selectedCategory = null,
                selectedPriority = null,
                isButtonActionEnable = false
            )
        }
    }
}