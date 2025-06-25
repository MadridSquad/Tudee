package com.washingtondcsquad.tudee.presentation.features.category_detail

import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.TaskUiState

data class CategoryDetailsScreenUiState(
    val categoryID: CategoryID = CategoryID(0L),
    val categoryTitle: String = "",
    val categoryImagePath: String = "",
    val isCategoryPredefined: Boolean = false,
    val inProgressTasks: List<TaskUiState> = emptyList(),
    val toDoTasks: List<TaskUiState> = emptyList(),
    val doneTasks: List<TaskUiState> = emptyList(),
    val isShowingEditCategoryBottomSheet: Boolean = false,
    val isShowingDeleteCategoryBottomSheet : Boolean= false,
    val errorMessage : String = "",
    val isLoading : Boolean = false

)