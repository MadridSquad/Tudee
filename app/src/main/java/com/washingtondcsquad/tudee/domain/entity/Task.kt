package com.washingtondcsquad.tudee.domain.entity

import java.time.LocalDate
import java.util.UUID

data class Task(
    val id: UUID,
    val categoryId: UUID,
    val title: String,
    val description: String,
    val date: LocalDate? = null,
    val status: String,
    val priority: Priority? = null,
)