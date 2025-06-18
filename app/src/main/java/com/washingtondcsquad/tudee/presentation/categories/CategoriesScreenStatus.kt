package com.washingtondcsquad.tudee.presentation.categories

data class CategoriesScreenStatus(
    val categories: List<Category> = listOf(),
) {
    data class Category(
        val id: Long,
        val title: String,
        val  iconPath : String,
        val tasksCount: Int = 0
    )
}