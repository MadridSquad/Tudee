package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.EditTaskUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EditTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    taskId: Int = 0,
    private val onCancelAddTaskBottomSheet: () -> Unit = {},
    private val onActionResult: (success: Boolean, message: String) -> Unit
) : BaseViewModel<EditTaskUiState>(
    EditTaskUiState(
        taskId = taskId,
    )
) {

    init {
        getAllCategories()
        getTaskById()
    }

    private fun getTaskById() {
        viewModelScope.launch {
            val task = tasksService.getTaskById(_uiState.value.taskId)
            withContext(Dispatchers.Main) {
                updateState {
                    copy(
                        taskId = task.id,
                        taskTitle = task.title,
                        taskDescription = task.description,
                        taskDate = task.date,
                        selectedPriority = task.priority,
                        selectedCategory = getCategory(task.categoryId),
                    )
                }
            }
        }


    }

    fun getAllCategories() {
        tryToExecute(
            request = {
                categoryService.getAllCategories().collect{
                    updateState {
                        copy( categoryList =  it )
                    }
                }
            },
            onSuccess = {

            },
            onError = { exception -> }
        )

    }

    fun getCategory(categoryId: Long): Category {
        return _uiState.value.categoryList.find { it.id == categoryId }!!
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

    fun updateButtonEnable() {
        updateState {
            copy(
                isButtonActionEnable =
                    _uiState.value.taskTitle.isNotEmpty() &&
                            _uiState.value.taskTitle.isNotBlank() &&
                            _uiState.value.selectedCategory != null &&
                            _uiState.value.selectedPriority != null
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
        val currentPriority = _uiState.value.selectedPriority
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
        val currentCategory = _uiState.value.selectedCategory
        if (currentCategory != category) {
            updateState {
                copy(
                    selectedCategory = category
                )
            }
            updateButtonEnable()
        }
    }

    fun onClickEditButton() {
        tryToExecute(
            request = {
                tasksService.editTask(
                    Task(
                        id = _uiState.value.taskId,
                        categoryId = _uiState.value.selectedCategory!!.id,
                        categoryImage = _uiState.value.selectedCategory!!.iconPath,
                        title = _uiState.value.taskTitle,
                        description = _uiState.value.taskDescription,
                        date = _uiState.value.taskDate,
                        status = TaskStatus.TODO,
                        priority = _uiState.value.selectedPriority!!,
                    )
                )
            },
            onSuccess = {
                clearDate()
                onCancelAddTaskBottomSheet()
                onActionResult(true,"Edited task successfully.")
            },
            onError = { exception ->
                onActionResult(false,exception.message.toString())
            }
        )
    }
    private fun clearDate() {
        updateState {
            copy(
                taskId = 0,
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