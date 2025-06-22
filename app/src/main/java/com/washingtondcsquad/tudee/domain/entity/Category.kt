package com.washingtondcsquad.tudee.domain.entity


@JvmInline
value class CategoryID(val categoryId: Long)
data class Category(
    val id: CategoryID,
    val title: String,
    var iconPath: ImageCategory,  //TODO move imageSource class from presentation to domain
    val isPredefined: Boolean, //TODO new task
)

sealed interface ImageCategory {
    data class AddedByUser(val value: String) : ImageCategory
    data class PredefinedDrawable(val id: Int) : ImageCategory
}