package com.washingtondcsquad.tudee.data.services

import android.content.Context
import com.washingtondcsquad.tudee.data.localSource.DaoCategory
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.deleteImageFromInternalStorage
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.saveImageToInternalStorage
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesServiceImpl(
    private val daoCategory: DaoCategory,
    private val context: Context

) : CategoriesService {
    override suspend fun createCategory(category: Category) {
       saveImageToInternalStorage(context,category.iconPath) {
            category.iconPath = it
        }
        daoCategory.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        deleteImageFromInternalStorage(context,category.iconPath)
        daoCategory.deleteCategory(category.toEntity())
    }

    override suspend fun editCategory(category: Category) {
        saveImageToInternalStorage(context,category.iconPath)
        daoCategory.editCategory(category.toEntity())
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return daoCategory.getAllCategories().map { flow -> flow.map { it.toDomain() } }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return daoCategory.getCategoryById(categoryId).toDomain()
    }
}