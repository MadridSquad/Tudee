package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.mapper.category.toCategory
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toCategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService

class CategoriesServiceImpl(
    private val localDataSource: LocalDataSource,
) : CategoriesService {
    override suspend fun createCategory(category: Category) {
        localDataSource.createCategory(category.toCategoryEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        localDataSource.deleteCategory(category.toCategoryEntity())
    }

    override suspend fun editCategory(category: Category) {
        localDataSource.editCategory(category.toCategoryEntity())
    }

    override suspend fun getAllCategories(): List<Category> {
        return localDataSource.getAllCategories().map { it.toCategory() }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return localDataSource.getCategoryById(categoryId).toCategory()
    }

}