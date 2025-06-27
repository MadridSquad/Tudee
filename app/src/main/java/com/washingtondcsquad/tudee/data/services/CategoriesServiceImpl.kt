package com.washingtondcsquad.tudee.data.services

import android.util.Log
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.data.localSource.daos.CategoryDao
import com.washingtondcsquad.tudee.data.localSource.daos.TaskDao
import com.washingtondcsquad.tudee.data.localSource.mapper.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.toEntity
import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.provider.StringProvider
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.ImageSaverService
import kotlinx.coroutines.flow.map

class CategoriesServiceImpl(
    private val stringProvider: StringProvider,
    private val categoryDao: CategoryDao,
    private val imageSaver: ImageSaverService,
    private val taskDao: TaskDao,
) : CategoriesService {

    override suspend fun createCategory(
        title: String,
        iconPath: String,
    ) {
        val category = CategoryEntity(
            title = title,
            id = CategoryID(0L), //TODO search how not send id because it's auto generation ???
            image = saveCategoryImage(iconPath),
            isPredefined = false,
        )
        categoryDao.createCategory(category)
        Log.e("save category", "done")
    }

    override suspend fun saveCategoryImage(iconPath: String): String {
        var savedUrl = ""
        imageSaver.saveImage(iconPath) { path ->
            savedUrl = path
        }
        return savedUrl
    }


    override suspend fun deleteCategory(categoryId: CategoryID) { //TODO delete image in presentation layer
        categoryDao.deleteCategory(categoryId)
    }

    override suspend fun deleteCategoryImage(iconPath: ImageSource) {
        when (val icon = iconPath) {
            is ImageSource.AddedByUser -> {
                imageSaver.deleteImage(icon.value)
            }

            is ImageSource.PredefinedDrawable -> {

            }
        }
    }

    override suspend fun editCategory(category: Category) {
        categoryDao.editCategory(category.toEntity())
    }

    override fun getAllCategories() =
        categoryDao.getAllCategories().map { flow -> flow.map { it.toDomain() } }

    override suspend fun getTaskCountByCategoryID(categoryId: CategoryID) =
        taskDao.getTaskCountByCategoryId(categoryId)


    override suspend fun getTasksByCategoryID(categoryId: CategoryID): List<Task> {
        return taskDao.getTasksByCategoryId(categoryId)
    }

    override suspend fun getCategoryById(categoryId: CategoryID): Category {
        return categoryDao.getCategoryById(categoryId).toDomain()
    }

    override suspend fun createPreDefinedCategories() {
        val categories = preparePredefinedCategories(stringProvider)
        categoryDao.createPredefinedCategories(categories)
    }

    private fun preparePredefinedCategories(stringProvider: StringProvider): List<CategoryEntity> {
        val categories = listOf(
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.education.toString(),
                image = R.drawable.education_icon.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.shopping.toString(),
                image = R.drawable.shopping.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.medical.toString(),
                image = R.drawable.medical.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.gym.toString(),
                image = R.drawable.gym.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.entertainment.toString(),
                image = R.drawable.entertainment.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.cooking.toString(),
                image = R.drawable.cooking.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.family.toString(),
                image = R.drawable.family.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.traveling.toString(),
                image = R.drawable.traveling.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.agriculture.toString(),
                image = R.drawable.agriculture.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.coding.toString(),
                image = R.drawable.coding.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.adoration.toString(),
                image = R.drawable.adoration.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.fix_bug.toString(),
                image = R.drawable.fix_bug.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.cleaning.toString(),
                image = R.drawable.cleaning.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.work.toString(),
                image = R.drawable.work.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.budgeting.toString(),
                image = R.drawable.budgeting.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.self_care.toString(),
                image = R.drawable.self_care.toString(),
                isPredefined = true
            ),
            CategoryEntity(
                id = CategoryID(0L),
                title = R.string.event.toString(),
                image = R.drawable.event.toString(),
                isPredefined = true
            )
        )
        return categories
    }
}