package com.washingtondcsquad.tudee.presentation.categories.mapper

import com.washingtondcsquad.tudee.domain.entity.Category
import com.washingtondcsquad.tudee.presentation.categories.CategoriesScreenStatus

fun Category.toCategoriesScreenStatus(): CategoriesScreenStatus.Category {
  return  CategoriesScreenStatus.Category(
      title = title,
      iconPath = icon,
      tasksCount = taskCount,
      id = id
  )
}

fun CategoriesScreenStatus.Category.toCategory(): Category {
    return Category(
        title = title,
        icon = iconPath,
        taskCount = tasksCount,
        id = id
    )
}

