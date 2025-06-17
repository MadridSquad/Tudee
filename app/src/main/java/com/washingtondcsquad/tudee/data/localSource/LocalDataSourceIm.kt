package com.washingtondcsquad.tudee.data.localSource

import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity
import com.washingtondcsquad.tudee.data.services.LocalDataSource

class LocalDataSourceIm (
    private val tudeeDaoCategory: TudeeDaoCategory
): LocalDataSource {

    override suspend fun createCategory(category: CategoryEntity) {
        tudeeDaoCategory.createCategory(category)
    }

    override suspend fun deleteCategory(categoryId: CategoryEntity) {
        tudeeDaoCategory.deleteCategory(categoryId)
    }

    override suspend fun editCategory(category: CategoryEntity) {
        tudeeDaoCategory.editCategory(category)
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return tudeeDaoCategory.getAllCategories()
    }

    override suspend fun getCategoryById(categoryId: Long): CategoryEntity {
        return tudeeDaoCategory.getCategoryById(categoryId)
    }



}