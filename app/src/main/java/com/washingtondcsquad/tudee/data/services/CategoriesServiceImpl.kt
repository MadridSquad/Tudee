package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService

class CategoriesServiceImpl(
    private val categoryLocalDataSource: CategoryLocalDataSource,
) : CategoriesService {
    override suspend fun createCategory(category: Category) {
        categoryLocalDataSource.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryLocalDataSource.deleteCategory(category.toEntity())
    }

    override suspend fun editCategory(category: Category) {
        categoryLocalDataSource.editCategory(category.toEntity())
    }

    override suspend fun getAllCategories(): List<Category> {
        return categoryLocalDataSource.getAllCategories().map { it.toDomain() }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return categoryLocalDataSource.getCategoryById(categoryId).toDomain()
    }

}