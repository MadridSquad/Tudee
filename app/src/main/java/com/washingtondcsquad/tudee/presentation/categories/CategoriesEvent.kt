package com.washingtondcsquad.tudee.presentation.categories

interface CategoriesEvent {
    fun onCategoryClick(category: CategoriesScreenStatus.Category)
    fun addCategoryClick(title: String, categoryIconPath: String)

}