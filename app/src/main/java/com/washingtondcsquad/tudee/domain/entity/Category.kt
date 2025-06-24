package com.washingtondcsquad.tudee.domain.entity


@JvmInline
value class CategoryID(val categoryId: Long)
data class Category(
    val id: CategoryID,
    val title: String,
    var iconPath: ImageSource,
    val isPredefined: Boolean, //TODO new task
)

sealed interface ImageSource {
    data class AddedByUser(val value: String) : ImageSource
    data class PredefinedDrawable(val id: Int) : ImageSource
}