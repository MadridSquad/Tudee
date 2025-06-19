package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.data.localSource.daos.DaoCategory
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService

class CategoriesServiceImpl(
    private val daoCategory: DaoCategory
) : CategoriesService {

    override suspend fun createCategory(category: Category) {
        daoCategory.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        daoCategory.deleteCategory(category.toEntity())
    }

    override suspend fun editCategory(category: Category) {
        daoCategory.deleteCategory(category.toEntity())
    }

    override suspend fun getAllCategories(): List<Category> {
        return daoCategory.getAllCategories().map { it.toDomain() }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return daoCategory.getCategoryById(categoryId).toDomain()
    }

}