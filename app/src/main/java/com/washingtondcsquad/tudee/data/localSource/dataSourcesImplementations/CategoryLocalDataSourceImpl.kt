package com.washingtondcsquad.tudee.data.localSource.dataSourcesImplementations

import com.washingtondcsquad.tudee.data.dataSources.CategoryLocalDataSource
import com.washingtondcsquad.tudee.data.localSource.daos.DaoCategory
import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity

class CategoryLocalDataSourceImpl(
    private val daoCategory: DaoCategory
) : CategoryLocalDataSource {


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