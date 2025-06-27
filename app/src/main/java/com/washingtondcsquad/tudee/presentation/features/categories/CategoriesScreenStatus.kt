package com.washingtondcsquad.tudee.presentation.features.categories

import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource


data class CategoriesScreenStatus(
    val categories: List<CategoryUiState> = emptyList(),

    )

data class CategoryUiState(
    val id: CategoryID,
    val title: String,
    var iconPath: ImageSource,
    val isPredefined: Boolean,
    val taskCount: Int
)