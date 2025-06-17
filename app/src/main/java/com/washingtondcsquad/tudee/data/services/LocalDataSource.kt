package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity

interface LocalDataSource {
    suspend fun createCategory(category: CategoryEntity)
    suspend fun deleteCategory(categoryId: CategoryEntity)
    suspend fun editCategory(category: CategoryEntity)
    suspend fun getAllCategories(): List<CategoryEntity>
    suspend fun getCategoryById(categoryId: Long): CategoryEntity
}