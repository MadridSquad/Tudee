package com.washingtondcsquad.tudee.presentation.features.categories

import com.washingtondcsquad.tudee.domain.entity.Category


data class CategoriesScreenStatus(
    val categories: List<Category> = listOf(),
)