package com.washingtondcsquad.tudee.data.localSource

import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity
import com.washingtondcsquad.tudee.data.services.LocalDataSource

class LocalDataSourceImpl (
    private val daoCategory: DaoCategory
): LocalDataSource {


    override suspend fun createCategory(category: CategoryEntity) {
        daoCategory.createCategory(category)
    }

    override suspend fun deleteCategory(category: CategoryEntity) {
        daoCategory.deleteCategory(category)
    }

    override suspend fun editCategory(category: CategoryEntity) {
        daoCategory.editCategory(category)
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return daoCategory.getAllCategories()
    }

    override suspend fun getCategoryById(categoryId: Long): CategoryEntity {
        return daoCategory.getCategoryById(categoryId)
    }



}