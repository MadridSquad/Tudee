package com.washingtondcsquad.tudee.data.services


import android.content.Context
import com.washingtondcsquad.tudee.data.localSource.daos.CategoryDao
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.DeleteImageFromInternalStorage
import com.washingtondcsquad.tudee.data.localSource.imageStorageManager.SaveImageToInternalStorage
import com.washingtondcsquad.tudee.data.localSource.mapper.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.compose.getKoin

class CategoriesServiceImpl(
    private val applicationContext: Context // this is Application context via androidContext()
    private val daoCategory: CategoryDao,
    private val saveImageToInternalStorage: SaveImageToInternalStorage,
    private val deleteImageFromInternalStorage: DeleteImageFromInternalStorage,
    ) : CategoriesService {
    override suspend fun createCategory(category: Category) {
        saveImageToInternalStorage.saveImageToInternalStorage(category.iconPath) {
            category.iconPath = it
        }
        daoCategory.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: CategoryID) {
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

    override suspend fun getTaskCountByCategoryID(categoryId: CategoryID): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksByCategoryID(categoryId: CategoryID): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun createPreDefinedCategories() {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryById(categoryId: CategoryID): Category {
        return daoCategory.getCategoryById(categoryIKd).toDomain()
    }
}