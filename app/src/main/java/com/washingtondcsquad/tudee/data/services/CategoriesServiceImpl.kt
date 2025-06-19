package com.washingtondcsquad.tudee.data.services


import com.washingtondcsquad.tudee.data.localSource.daos.DaoCategory
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.DeleteImageFromInternalStorage
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.SaveImageToInternalStorage
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesServiceImpl(
    private val daoCategory: DaoCategory,
    private val saveImageToInternalStorage: SaveImageToInternalStorage,
    private val deleteImageFromInternalStorage: DeleteImageFromInternalStorage,

    ) : CategoriesService {
    override suspend fun createCategory(category: Category) {
        saveImageToInternalStorage.saveImageToInternalStorage(category.iconPath) {
            category.iconPath = it
        }
        daoCategory.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        deleteImageFromInternalStorage.deleteImageFromInternalStorage(category.iconPath)
        daoCategory.deleteCategory(category.toEntity())
    }

    override suspend fun editCategory(category: Category) {
        saveImageToInternalStorage.saveImageToInternalStorage(category.iconPath)
        daoCategory.editCategory(category.toEntity())
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return daoCategory.getAllCategories().map { flow -> flow.map { it.toDomain() } }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return daoCategory.getCategoryById(categoryId).toDomain()
    }
}