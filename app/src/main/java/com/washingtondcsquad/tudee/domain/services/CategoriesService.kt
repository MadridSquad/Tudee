package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesService {
    suspend fun createCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun editCategory(category: Category)
     fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(categoryId: Long): Category
}