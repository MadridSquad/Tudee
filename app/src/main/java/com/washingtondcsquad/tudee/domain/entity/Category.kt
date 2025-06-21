package com.washingtondcsquad.tudee.domain.entity

import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource


@JvmInline
value class CategoryID(val categoryId:Long)
data class Category(
    val id: CategoryID,
    val title: String,
    var iconPath: ImageSource,  //TODO move imageSource class from presentation to domain
    val taskCount: Int, // delete it from here or not ?
    val isPredefined: Boolean //TODO new task
)
