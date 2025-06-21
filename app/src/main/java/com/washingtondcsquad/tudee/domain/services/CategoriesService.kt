package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource
import kotlinx.coroutines.flow.Flow

interface CategoriesService {
    suspend fun createCategory(
        title: String,
        iconPath: ImageSource,
        isPredefined: Boolean,
        taskCounts: Int
    )
    suspend fun deleteCategory(category: Category) //TODO write query to delete using id rather than send the whole category
    suspend fun editCategory(category: Category)
    fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(categoryId: CategoryID): Category
}