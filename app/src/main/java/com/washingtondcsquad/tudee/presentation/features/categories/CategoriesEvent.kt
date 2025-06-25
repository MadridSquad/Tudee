package com.washingtondcsquad.tudee.presentation.features.categories

import com.washingtondcsquad.tudee.domain.entity.Category

interface CategoriesEvent {
    fun onCategoryClick(category: Category)
    fun addCategoryClick(title: String, categoryIconPath: String)

}