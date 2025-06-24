package com.washingtondcsquad.tudee.domain.entity

import kotlinx.datetime.LocalDate

@JvmInline
value class TaskID(val taskId: Long)
data class Task(
    val id: TaskID,
    val categoryId: TaskID,
    val title: String,
    val description: String,
    val date: LocalDate,
    val status: TaskStatus,
    val priority: Priority,
)