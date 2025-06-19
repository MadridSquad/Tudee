package com.washingtondcsquad.tudee.domain.entity


data class Category(
    val id: Long,
    val title: String,
    var iconPath: String,
    val taskCount: Int,
)