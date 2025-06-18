package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.mapper.category.toCategory
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toCategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService

class CategoriesServiceImpl(
    private val categoryLocalDataSource: CategoryLocalDataSource,
) : CategoriesService {
    override suspend fun createCategory(category: Category) {
        categoryLocalDataSource.createCategory(category.toCategoryEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryLocalDataSource.deleteCategory(category.toCategoryEntity())
    }

    override suspend fun editCategory(category: Category) {
        categoryLocalDataSource.editCategory(category.toCategoryEntity())
    }

    override suspend fun getAllCategories(): List<Category> {
        return categoryLocalDataSource.getAllCategories().map { it.toCategory() }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return categoryLocalDataSource.getCategoryById(categoryId).toCategory()
    }

}