package com.washingtondcsquad.tudee.presentation.features.edit_task

import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.provider.StringProvider
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.EditTaskUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class EditTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    private val stringProvider: StringProvider,
    taskId: Int,
) : BaseViewModel<EditTaskUiState>(
    EditTaskUiState(taskId = taskId)
) {

    init {
        getAllCategories()
        getTaskById()
    }

    private fun getTaskById() {
        tryToExecute(
            request = {
                tasksService.getTaskById(TaskID(state.value.taskId.toLong()))
            },
            onSuccess = { task ->
                updateState {
                    copy(
                        taskTitle = task.title,
                        taskDescription = task.description,
                        taskDate = task.date,
                        selectedPriority = task.priority,
                        selectedCategory = state.value.categoryList.find { it.id == task.categoryId }
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

    private fun getAllCategories() {
        tryToCollect(
            request = { categoryService.getAllCategories() },
            onChange = { newCategoryList ->
                updateState {
                    copy(categoryList = newCategoryList)
                }
            },
            onError = { }
        )
    }

    fun onTitleChange(newTitle: String) {
        updateState { copy(taskTitle = newTitle) }
        updateButtonEnable()
    }

    fun onDescriptionChange(newDescription: String) {
        updateState { copy(taskDescription = newDescription) }
    }

    fun onShowDatePicker() {
        updateState { copy(isDatePickerDisplayed = true) }
    }

    fun onHideDatePicker() {
        updateState { copy(isDatePickerDisplayed = false) }
    }

    fun onDateSelected(dateAsMilliseconds: Long) {
        val realDate = Instant.fromEpochMilliseconds(dateAsMilliseconds)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
        updateState { copy(taskDate = realDate) }
    }

    fun onPrioritySelected(priority: Priority) {
        updateState { copy(selectedPriority = priority) }
        updateButtonEnable()
    }

    fun onCategorySelected(category: Category) {
        updateState { copy(selectedCategory = category) }
        updateButtonEnable()
    }

    private fun updateButtonEnable() {
        updateState {
            copy(
                isButtonActionEnable =
                    state.value.taskTitle.isNotBlank() &&
                            state.value.selectedPriority != null &&
                            state.value.selectedCategory != null
            )
        }
    }

    fun onClickEditButton(
        onSuccess: (message: String) -> Unit,
        onError: (message: String) -> Unit,
    ) {
        val priority = state.value.selectedPriority
        val category = state.value.selectedCategory

        if (priority == null || category == null) {
            onError(stringProvider.getString(R.string.some_error_happened))
            return
        }

        tryToExecute(
            request = {
                tasksService.editTask(
                    Task(
                        id = TaskID(state.value.taskId.toLong()),
                        categoryId = category.id,
                        title = state.value.taskTitle,
                        description = state.value.taskDescription,
                        date = state.value.taskDate,
                        status = TaskStatus.TODO,
                        priority = priority
                    )
                )
            },
            onSuccess = {
                onSuccess(stringProvider.getString(R.string.edit_task_successfully))
                clearState()
            },
            onError = {
                onError(stringProvider.getString(R.string.some_error_happened))
            }
        )
    }

    private fun clearState() {
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