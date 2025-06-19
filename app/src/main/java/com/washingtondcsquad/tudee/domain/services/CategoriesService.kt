package com.washingtondcsquad.tudee.domain.services

import com.washingtondcsquad.tudee.domain.entity.Category

interface CategoriesService {
    suspend fun createCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun editCategory(category: Category)
    suspend fun getAllCategories(): List<Category>
    suspend fun getCategoryById(categoryId: Long): Category
}