package com.washingtondcsquad.tudee.data.dataSources

import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity

interface CategoryLocalDataSource {
    suspend fun createCategory(category: CategoryEntity)
    suspend fun deleteCategory(category: CategoryEntity)
    suspend fun editCategory(category: CategoryEntity)
    suspend fun getAllCategories(): List<CategoryEntity>
    suspend fun getCategoryById(categoryId: Long): CategoryEntity
}