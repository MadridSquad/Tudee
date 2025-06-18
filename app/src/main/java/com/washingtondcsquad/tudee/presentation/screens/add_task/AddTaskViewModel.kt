package com.washingtondcsquad.tudee.presentation.screens.add_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.washingtondcsquad.tudee.data.localSource.CategoryLocalDataSourceImpl
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import com.washingtondcsquad.tudee.domain.services.TasksService
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
    private val categoryService: CategoryLocalDataSourceImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllCategories()
    }

    fun  getAllCategories(){
        viewModelScope.launch {
          val allCategory =  categoryService.getAllCategories().map {
              it.toDomain()
          }
            _uiState.update { it.copy(categoryList = allCategory) }
        }
    }

    fun onShowDatePicker(){
        _uiState.update { it.copy(isDatePickerDisplayed = true) }
    }

    fun onHideDatePicker(){
        _uiState.update { it.copy(isDatePickerDisplayed = false) }
    }

    fun updateButtonEnable() {
        _uiState.update {
            it.copy(
                isButtonActionEnable =
                    _uiState.value.taskTitle.isNotEmpty() &&
                    _uiState.value.taskTitle.isNotBlank() &&
                    _uiState.value.selectedCategory != null &&
                    _uiState.value.selectedPriority != null
            )
        }
    }

    fun onTitleChange(newTitle: String) {
        _uiState.update { it.copy(taskTitle = newTitle) }
        updateButtonEnable()
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
        val currentPriority = uiState.value.selectedPriority
        if (currentPriority?.name != priority.name) {
            _uiState.update { it.copy(selectedPriority = priority) }
            updateButtonEnable()
        }
    }

    fun onCategorySelected(category: Category) {
        val currentCategory = uiState.value.selectedCategory
        if (currentCategory != category) {
            _uiState.update { it.copy(selectedCategory = category) }
            updateButtonEnable()
        }
    }

    fun onClickSaveButton() {
        viewModelScope.launch {
            tasksService.createTask(
                Task(
                    id = UUID.randomUUID(),
                    title = _uiState.value.taskTitle,
                    description =_uiState.value.taskDescription,
                    date = _uiState.value.taskDate,
                    priority = _uiState.value.selectedPriority,
                    categoryId = _uiState.value.selectedCategory!!.id,
                    status = TaskStatus.TODO.name,
                )
            )
        }
    }
}