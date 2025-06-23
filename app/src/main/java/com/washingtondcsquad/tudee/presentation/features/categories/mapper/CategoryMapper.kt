package com.washingtondcsquad.tudee.presentation.features.categories.mapper

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.presentation.features.categories.CategoriesScreenStatus
import com.washingtondcsquad.tudee.presentation.features.sharedUiState.ImageSource

fun Category.toUi(): CategoriesScreenStatus.Category {
  return  CategoriesScreenStatus.Category(
      title = title,
      iconPath = iconPath.toString(),
      tasksCount = taskCount,
      id = id.categoryId
  )
}

fun CategoriesScreenStatus.Category.toDomain(): Category {
    return Category(
        title = title,
        iconPath = ImageSource.Path(iconPath), //TODO check it
        taskCount = tasksCount,
        id = CategoryID(id),
        isPredefined = TODO()
    )
}

