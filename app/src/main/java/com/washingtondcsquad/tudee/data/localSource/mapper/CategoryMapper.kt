package com.washingtondcsquad.tudee.data.localSource.mapper

import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource


fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = CategoryID(0L),
        title = title,
        image = iconPath.toString(),
        taskCount = taskCount,
        isPredefined = TODO()
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        title = title,
        iconPath = ImageSource.Path(image), //TODO check this
        taskCount = taskCount,
        isPredefined = TODO()
    )
}