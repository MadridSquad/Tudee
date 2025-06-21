package com.washingtondcsquad.tudee.presentation.features.categories.mapper

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesScreenStatus

fun Category.toUi(): CategoriesScreenStatus.Category {
  return  CategoriesScreenStatus.Category(
      title = title,
      iconPath = iconPath,
      tasksCount = taskCount,
      id = id
  )
}

fun CategoriesScreenStatus.Category.toDomain(): Category {
    return Category(
        title = title,
        iconPath = iconPath,
        taskCount = tasksCount,
        id = id
    )
}

