package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import java.util.UUID

class CategoriesServiceImpl : CategoriesService {
    override suspend fun createCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCategory(categoryId: UUID) {
        TODO("Not yet implemented")
    }

    override suspend fun editCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCategories(): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryById(categoryId: UUID): Category {
        TODO("Not yet implemented")
    }
}