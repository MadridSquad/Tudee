package com.washingtondcsquad.tudee.presentation.screens.add_task

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.AddTaskUiState
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class AddTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    initialDate: LocalDate
) : BaseViewModel<AddTaskUiState>(AddTaskUiState(taskDate = initialDate)) { //ViewModel() {

    init {
        getAllCategories()
    }


    fun getAllCategories() {
        var  allCategories : List<Category> =emptyList()
        tryToExecute(
            request = {
                allCategories = categoryService.getAllCategories()
            },
            onSuccess = {
                updateState {
                    copy(
                        categoryList = allCategories
                    )
                }
            },
            onError = { exception -> }
        )

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
                    _state.value.taskTitle.isNotEmpty() &&
                            _state.value.taskTitle.isNotBlank() &&
                            _state.value.selectedCategory != null &&
                            _state.value.selectedPriority != null
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
        val realDate: LocalDate = Instant.ofEpochMilli(dateAsMilliseconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
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
            updateButtonEnable()
        }
    }

    fun onClickSaveButton() {
        tryToExecute(
            request = {
                tasksService.createTask(
                    Task(
                        id = 0,
                        categoryId = _state.value.selectedCategory!!.id,
                        categoryImage = _state.value.selectedCategory!!.icon,
                        title = _state.value.taskTitle,
                        description = _state.value.taskDescription,
                        date = _state.value.taskDate.toString(),
                        status =TaskStatus.TODO,
                        priority = _state.value.selectedPriority!!,
                    )
                )
            },
            onSuccess = {},
            onError = { exception -> }
        )
    }
}