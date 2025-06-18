package com.washingtondcsquad.tudee.data.localSource.mapper.category

import com.washingtondcsquad.tudee.data.localSource.entities.CategoryEntity
import com.washingtondcsquad.tudee.domain.entity.Category


fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        title = title,
        image = icon,
        taskCount = taskCount

    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        title = title,
        icon = image,
        taskCount = taskCount
    )
}