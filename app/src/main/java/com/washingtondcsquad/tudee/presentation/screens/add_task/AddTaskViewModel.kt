package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.domain.usecase.CreateTaskUseCase
import com.washingtondcsquad.tudee.domain.usecase.GetCategoriesUseCase
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID


class AddTaskViewModel(
    private val tasksService: TasksService,
) : ViewModel()
{
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState = _uiState.asStateFlow()

    val availablePriorities: List<Priority> = Priority.entries

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

    fun onPrioritySelected(priority: Priority) {
        _uiState.update { currentState ->
            currentState.copy(taskPriority = priority)
        }
    }

    fun onCategorySelected(category: Category) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun saveTask(currentState: TaskUiState) {
        viewModelScope.launch {
                createTaskUseCase(
                    title = currentState.taskTitle,
                    description = currentState.taskDescription,
                    date = currentState.taskDate,
                    priority = currentState.taskPriority,
                    category = currentState.selectedCategory
                )
        }
    }
}