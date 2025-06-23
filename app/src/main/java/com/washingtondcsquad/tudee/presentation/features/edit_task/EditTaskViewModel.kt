package com.washingtondcsquad.tudee.presentation.features.edit_task

import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.EditTaskUiState
import com.washingtondcsquad.tudee.presentation.provider.StringProvider
import kotlinx.datetime.Instant
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class EditTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    taskId: Int,
    private val stringProvider: StringProvider,
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
        tryToExecute(
            request = {
                tasksService.getTaskById (
                    TaskID(state.value.taskId.toLong())
                )
            },
            onSuccess = { task ->
                updateState {
                    copy(
                        taskTitle = task.title,
                        taskDescription = task.description,
                        taskDate = task.date,
                        selectedCategory = getCategory(CategoryID(task.categoryId)),
                        selectedPriority = task.priority,
                    )
                }
                updateButtonEnable()
            },
            onError = { exception ->
                updateState {
                    copy(errorMessage = exception.message)
                }
            }
        )
    }

    fun getAllCategories() {
        tryToExecute(
            request = {
                categoryService.getAllCategories().collect {
                    updateState {
                        copy(categoryList = it)
                    }
                }
            },
            onSuccess = {

            },
            onError = { exception -> }
        )

    }

    fun getCategory(categoryId: CategoryID): Category {
        return state.value.categoryList.find { it.id == categoryId }!!
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
                    state.value.taskTitle.isNotEmpty() &&
                            state.value.taskTitle.isNotBlank() &&
                            state.value.selectedCategory != null &&
                            state.value.selectedPriority != null
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

    fun onClickEditButton(
        onSuccess: (message: String) -> Unit,
        onError: (message: String) -> Unit,
    ) {
        tryToExecute(
            request = {
                tasksService.editTask(
                    Task(
                        title = state.value.taskTitle,
                        description = state.value.taskDescription,
                        date = state.value.taskDate,
                        priority = state.value.selectedPriority!!,
                        id = TaskID(state.value.taskId.toLong()),
                        categoryId = state.value.selectedCategory!!.id.categoryId,
                        status = TaskStatus.TODO
                    )
                )
            },
            onSuccess = {
                onSuccess(stringProvider.getString(R.string.edit_task_successfully))
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
                taskTitle = "",
                taskDescription = "",
                taskDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                selectedCategory = null,
                selectedPriority = null,
                isButtonActionEnable = false
            )
        }
    }
}