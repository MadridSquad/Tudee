package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.UUID
import java.time.Instant
import java.time.ZoneId


class AddTaskViewModel(
    private val tasksService: TasksService,
) : ViewModel()
{
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(taskTitle = newTitle) }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(taskDescription = newDescription) }
    }

    fun onDateSelected(dateAsMilliseconds: Long) {
        val realDate: LocalDate = Instant.ofEpochMilli(dateAsMilliseconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        _uiState.update { it.copy(taskDate = realDate) }
    }

    fun onPriorityChange(newPriority: Priority) {
        _uiState.update { it.copy(taskPriority = newPriority) }
    }

    fun onCategorySelected(category: Category) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun saveTask() {
        val currentUiState = uiState.value

        if (currentUiState.selectedCategory == null ||
            currentUiState.taskPriority == null ||
            currentUiState.taskDate == null) {

            _uiState.update { it.copy(errorMessage = "Please fill all the fields") }
            return
        }

        val newTask = Task(
            id = UUID.randomUUID(),
            categoryId = currentUiState.selectedCategory!!.id,
            title = currentUiState.taskTitle,
            description = currentUiState.taskDescription,
            date = currentUiState.taskDate,
            status = "Pending",
            priority = currentUiState.taskPriority
        )

        viewModelScope.launch {
            tasksService.createTask(newTask)
        }
    }


    fun errorShown() {
        _uiState.update { it.copy(errorMessage = null) }
    }


}