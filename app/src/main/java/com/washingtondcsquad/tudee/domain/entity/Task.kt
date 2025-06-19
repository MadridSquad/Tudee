package com.washingtondcsquad.tudee.domain.entity

import java.time.LocalDate

data class Task(
    val id: Int,
    val categoryId: Int,
    val title: String,
    val description: String,
    val date: LocalDate,
    val status: TaskStatus,
    val priority: Priority,
)