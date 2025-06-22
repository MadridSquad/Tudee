package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageCategory
import com.washingtondcsquad.tudee.domain.entity.Task
import kotlinx.coroutines.flow.Flow

interface CategoriesService {
    suspend fun createCategory(
        title: String,
        iconPath: ImageCategory,
        isPredefined: Boolean,
    )

    suspend fun deleteCategory(category: CategoryID)

    suspend fun editCategory(category: Category)

    fun getAllCategories(): Flow<List<Category>>

    suspend fun getCategoryById(categoryId: CategoryID): Category

    suspend fun getTaskCountByCategoryID(categoryId: CategoryID): Int

    suspend fun getTasksByCategoryID(categoryId: CategoryID): List<Task>
}