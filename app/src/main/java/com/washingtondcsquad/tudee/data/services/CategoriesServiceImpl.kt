package com.washingtondcsquad.tudee.data.services


import com.washingtondcsquad.tudee.data.localSource.daos.CategoryDao
import com.washingtondcsquad.tudee.data.localSource.mapper.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.ImageSaverService
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesServiceImpl(
    private val daoCategory: CategoryDao,
    private val imageSaver: ImageSaverService,

    ) : CategoriesService {

    override suspend fun createCategory(
        title: String, iconPath: ImageSource, isPredefined: Boolean, taskCounts: Int
    ) {
        val category = Category(
            title = title,
            id = CategoryID(0L),
            iconPath = iconPath,
            taskCount = 0,
            isPredefined = true,
        )
        imageSaver.saveImage(category.iconPath) {
            category.iconPath = ImageSource.Path(it) //TODO check it
        }
        daoCategory.createCategory(category.toEntity())

    }

    override suspend fun deleteCategory(category: Category) {
        imageSaver.deleteImage(category.iconPath)
        daoCategory.deleteCategory(category.toEntity())
    }

    override suspend fun editCategory(category: Category) {
        imageSaver.deleteImage(category.iconPath)
        daoCategory.editCategory(category.toEntity())
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return daoCategory.getAllCategories().map { flow -> flow.map { it.toDomain() } }
    }

    override suspend fun getCategoryById(categoryId: CategoryID): Category {
        return daoCategory.getCategoryById(categoryId).toDomain()
    }
}