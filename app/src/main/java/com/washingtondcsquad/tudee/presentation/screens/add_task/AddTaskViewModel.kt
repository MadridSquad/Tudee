package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.lifecycle.ViewModel
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.services.TasksService
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.LocalDate
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

    fun onPrioritySelected( priority: Priority ){
        val currentPriority = uiState.value.selectedPriority
        if (currentPriority?.name != priority.name)
            _uiState.update { it.copy(selectedPriority = priority) }
    }

    fun onCategorySelected(category: Category) {
        val currentCategory = uiState.value.selectedCategory
        if (currentCategory != category)
            _uiState.update { it.copy(selectedCategory = category) }
    }



//    fun saveTask(currentState: TaskUiState) {
//        viewModelScope.launch {
//            tasksService.createTask(
//                Task(
//                    id = UUID.randomUUID(),
//                    title = currentState.taskTitle,
//                    description = currentState.taskDescription,
//                    date = currentState.taskDate,
//                    priority = currentState.taskPriority,
//                    categoryId = TODO(),
//                    status = TODO(),
//                )
//            )
//        }
//
//
//    }
}