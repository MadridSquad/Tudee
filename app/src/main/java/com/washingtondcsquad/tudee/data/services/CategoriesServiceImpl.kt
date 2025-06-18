package com.washingtondcsquad.tudee.data.services

import com.washingtondcsquad.tudee.data.localSource.mapper.category.toDomain
import com.washingtondcsquad.tudee.data.localSource.mapper.category.toEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.services.CategoriesService
import java.util.UUID

class CategoriesServiceImpl(
    private val categoryLocalDataSource: CategoryLocalDataSource,
) : CategoriesService {
    override suspend fun createCategory(category: Category) {
        categoryLocalDataSource.createCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category) {
        categoryLocalDataSource.deleteCategory(category.toEntity())
    }

    override suspend fun editCategory(category: Category) {
        categoryLocalDataSource.editCategory(category.toEntity())
    }

    override suspend fun getAllCategories(): List<Category> {
//        return categoryLocalDataSource.getAllCategories().map { it.toDomain() }
        return listOf(
            // Row 1
            Category(id = UUID.randomUUID(), title = "Education", image = "education_icon", taskCount = 12),
            Category(id = UUID.randomUUID(), title = "Shopping", image = "education_icon", taskCount = 8),
            Category(id = UUID.randomUUID(), title = "Medical", image = "education_icon", taskCount = 3),

            // Row 2
            Category(id = UUID.randomUUID(), title = "Gym", image = "education_icon", taskCount = 15),
            Category(id = UUID.randomUUID(), title = "Entertainment", image = "education_icon", taskCount = 6),
            Category(id = UUID.randomUUID(), title = "Cooking", image = "education_icon", taskCount = 9),

            // Row 3
            Category(id = UUID.randomUUID(), title = "Work", image = "education_icon", taskCount = 25),
            Category(id = UUID.randomUUID(), title = "Travel", image = "education_icon", taskCount = 4),
            Category(id = UUID.randomUUID(), title = "Health", image = "education_icon", taskCount = 7),

            // Row 4
            Category(id = UUID.randomUUID(), title = "Personal", image = "education_icon", taskCount = 11),
            Category(id = UUID.randomUUID(), title = "Finance", image = "education_icon", taskCount = 5),
            Category(id = UUID.randomUUID(), title = "Social", image = "education_icon", taskCount = 13),

            // Row 5
            Category(id = UUID.randomUUID(), title = "Home", image = "education_icon", taskCount = 18),
            Category(id = UUID.randomUUID(), title = "Study", image = "education_icon", taskCount = 22),
            Category(id = UUID.randomUUID(), title = "Hobby", image = "education_icon", taskCount = 8),

            // Row 6
            Category(id = UUID.randomUUID(), title = "Family", image = "education_icon", taskCount = 14),
            Category(id = UUID.randomUUID(), title = "Business", image = "education_icon", taskCount = 19),
            Category(id = UUID.randomUUID(), title = "Learning", image = "education_icon", taskCount = 16),

            // Row 7
            Category(id = UUID.randomUUID(), title = "Creative", image = "education_icon", taskCount = 10),
            Category(id = UUID.randomUUID(), title = "Sports", image = "education_icon", taskCount = 12),
            Category(id = UUID.randomUUID(), title = "Reading", image = "education_icon", taskCount = 6),

            // Row 8
            Category(id = UUID.randomUUID(), title = "Music", image = "education_icon", taskCount = 9),
            Category(id = UUID.randomUUID(), title = "Photography", image = "education_icon", taskCount = 7),
            Category(id = UUID.randomUUID(), title = "Gaming", image = "education_icon", taskCount = 11),

            // Row 9
            Category(id = UUID.randomUUID(), title = "Fitness", image = "education_icon", taskCount = 17),
            Category(id = UUID.randomUUID(), title = "Technology", image = "education_icon", taskCount = 21),
            Category(id = UUID.randomUUID(), title = "Art", image = "education_icon", taskCount = 8),

            // Row 10
            Category(id = UUID.randomUUID(), title = "Outdoor", image = "education_icon", taskCount = 13),
            Category(id = UUID.randomUUID(), title = "Planning", image = "education_icon", taskCount = 15),
            Category(id = UUID.randomUUID(), title = "Meditation", image = "education_icon", taskCount = 5)
        )
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return categoryLocalDataSource.getCategoryById(categoryId).toDomain()
    }

}