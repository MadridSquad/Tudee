package com.washingtondcsquad.tudee.domain.entity


data class Category(
    val id: Long,
    val title: String,
    val icon: String,
    val taskCount: Int,
)