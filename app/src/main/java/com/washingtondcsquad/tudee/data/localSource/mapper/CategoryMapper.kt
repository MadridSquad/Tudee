package com.washingtondcsquad.tudee.data.localSource.mapper

import com.washingtondcsquad.tudee.data.localSource.model.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category


fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = 0L,
        title = title,
        image = iconPath,
        taskCount = taskCount

    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        title = title,
        iconPath = image,
        taskCount = taskCount
    )
}