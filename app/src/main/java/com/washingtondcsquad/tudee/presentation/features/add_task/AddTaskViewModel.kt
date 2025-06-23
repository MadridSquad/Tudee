package com.washingtondcsquad.tudee.presentation.features.add_task

import android.util.Log
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskID
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.base.BaseViewModel
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.AddTaskUiState
//import kotlinx.datetime.LocalDate
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import kotlinx.datetime.toKotlinLocalDate

import java.time.format.DateTimeFormatter

class AddTaskViewModel(
    private val tasksService: TasksService,
    private val categoryService: CategoriesService,
    taskDate: LocalDate = LocalDate.now(),
    private val onCancelAddTaskBottomSheet: () -> Unit,
    private val onActionResult:(success:Boolean,message:String)->Unit
) : BaseViewModel<AddTaskUiState>(
    AddTaskUiState(
        taskDate = taskDate.format(DateTimeFormatter.ofPattern("d-M-yyyy"))
    )
) {

    init {
        getAllCategories()
    }


    private fun getAllCategories() {
        tryToExecute(
            request = {
                 categoryService.getAllCategories().collect{
                     updateState {
                         copy(
                             categoryList = it
                         )
                     }
                }
            },
            onSuccess = {},
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
            tryToExecute(
                request = {
                    categoryService.editCategory(category.copy(taskCount = 1))
                },
                onSuccess = {},
                onError = { exception ->
                    Log.e("AddTaskViewModel", exception.message.toString())
                }
            )
        }
    }

    fun onClickSaveButton() {
        tryToExecute(
            request = {
                val parsedDate = LocalDate.parse(state.value.taskDate, DateTimeFormatter.ofPattern("d-M-yyyy"))
                tasksService.createTask(
                    Task(
                        id = TaskID(0L),
                        categoryId = state.value.selectedCategory!!.id.categoryId,
                        title = state.value.taskTitle,
                        description = state.value.taskDescription,
                        date =parsedDate.toKotlinLocalDate(),
                        status = TaskStatus.TODO,
                        priority = state.value.selectedPriority!!,
                    )
                )
            },
            onSuccess = {
                clearDate()
                onCancelAddTaskBottomSheet()
                onActionResult(true, "Add task successfully.")
            },
            onError = { exception ->
                onActionResult(false, exception.message.toString())
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