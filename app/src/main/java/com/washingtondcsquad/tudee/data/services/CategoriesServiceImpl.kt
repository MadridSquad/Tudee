package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.daos.CategoryDao
import com.washingtondcsquad.tudee.data.localSource.mapper.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import com.washingtondcsquad.tudee.domain.services.ImageSaverService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.washingtondcsquad.tudee.R
import com.washingtondcsquad.tudee.data.localSource.daos.TaskDao
import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.ImageSource
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.provider.StringProvider

class CategoriesServiceImpl(
    private val stringProvider: StringProvider,
    private val categoryDao: CategoryDao,
    private val imageSaver: ImageSaverService,
    private val taskDao : TaskDao

    ) : CategoriesService {

    override suspend fun createCategory(
        title: String,
        iconPath: String,
        isPredefined: Boolean
    ) {
        val category = Category(
            title = title,
            id = CategoryID(0L), //TODO search how not send id because it's auto generation ???
            iconPath = ImageSource.AddedByUser(saveCategoryImage(iconPath)),
            isPredefined = false,
        )
        categoryDao.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(categoryId: CategoryID) { //TODO delete image in presentation layer
        categoryDao.deleteCategory(categoryId)
    }

    override suspend fun editCategory(category: Category) {
        deleteCategoryImage(category.iconPath)
        categoryDao.editCategory(category.toEntity())
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { flow -> flow.map { it.toDomain() } }
    }

    override suspend fun getTaskCountByCategoryID(categoryId: CategoryID): Int {
        return taskDao.getTaskCountByCategoryId(categoryId)
    }

    override suspend fun getTasksByCategoryID(categoryId: CategoryID): List<Task> {
        return taskDao.getTasksByCategoryId(categoryId)
    }

    override suspend fun saveCategoryImage(iconPath: String): String {
        var savedUrl = ""
        imageSaver.saveImage(iconPath) { path->
            savedUrl = path
       }
        return savedUrl
    }

    override suspend fun deleteCategoryImage(iconPath: ImageSource) {
        when(val icon = iconPath){
            is ImageSource.AddedByUser ->{
                imageSaver.deleteImage(icon.value)
            }
            is ImageSource.PredefinedDrawable -> TODO()
        }
    }

    override suspend fun getCategoryById(categoryId: CategoryID): Category {
        return categoryDao.getCategoryById(categoryId).toDomain()
    }

    override suspend fun createPreDefinedCategories() {
        val categories = preparePredefinedCategories(stringProvider)
        categoryDao.createPredefinedCategories(categories)
    }
}

private fun preparePredefinedCategories(stringProvider: StringProvider): List<CategoryEntity>{
    val categories = listOf(
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.education),
            image = R.drawable.education_icon.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.shopping),
            image = R.drawable.shopping.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.medical),
            image = R.drawable.medical.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.gym),
            image = R.drawable.gym.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.entertainment),
            image = R.drawable.entertainment.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.cooking),
            image = R.drawable.cooking.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.family),
            image = R.drawable.family.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.traveling),
            image = R.drawable.traveling.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.agriculture),
            image = R.drawable.agriculture.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.coding),
            image = R.drawable.coding.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.adoration),
            image = R.drawable.adoration.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.fix_bug),
            image = R.drawable.fix_bug.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.cleaning),
            image = R.drawable.cleaning.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.work),
            image = R.drawable.work.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.budgeting),
            image = R.drawable.budgeting.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.self_care),
            image = R.drawable.self_care.toString(),
            isPredefined = true
        ),
        CategoryEntity(
            id = CategoryID(0L),
            title = stringProvider.getString(R.string.event),
            image = R.drawable.event.toString(),
            isPredefined = true
        )
    )
    return categories
}