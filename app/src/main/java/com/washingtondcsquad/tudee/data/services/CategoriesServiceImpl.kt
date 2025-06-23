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
import com.washingtondcsquad.tudee.domain.provider.StringProvider
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.compose.getKoin
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity

class CategoriesServiceImpl(
    private val stringProvider: StringProvider
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
        val titles = listOf(
            CategoryEntity(
                CategoryID(0L),
                stringProvider.getString(R.string.education),
                image = R.drawable.education_icon.toString(),
                isPredefined = true
            )

                    stringProvider . getString (R.string.shopping),
            stringProvider.getString(R.string.medical),
            stringProvider.getString(R.string.gym),
            stringProvider.getString(R.string.entertainment),
            stringProvider.getString(R.string.cooking),
            stringProvider.getString(R.string.family),
            stringProvider.getString(R.string.traveling),
            stringProvider.getString(R.string.agriculture),
            stringProvider.getString(R.string.coding),
            stringProvider.getString(R.string.adoration),
            stringProvider.getString(R.string.fix_bug),
            stringProvider.getString(R.string.cleaning),
            stringProvider.getString(R.string.work),
            stringProvider.getString(R.string.budgeting),
            stringProvider.getString(R.string.self_care),
            stringProvider.getString(R.string.event)
        )

        TODO("Not yet implemented")
    }

    override suspend fun getCategoryById(categoryId: CategoryID): Category {
        return daoCategory.getCategoryById(categoryIKd).toDomain()
    }
}