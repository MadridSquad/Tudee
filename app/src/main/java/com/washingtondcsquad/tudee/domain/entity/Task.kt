package com.washingtondcsquad.tudee.domain.entity

data class Task(
    val id: Int,
    val categoryId: Long,
    val categoryImage: String,
    val title: String,
    val description: String,
    val date: String,
    val status: TaskStatus,
    val priority: Priority,
)