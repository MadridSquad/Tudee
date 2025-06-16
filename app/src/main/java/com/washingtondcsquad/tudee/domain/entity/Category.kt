package com.washingtondcsquad.tudee.domain.entity

import java.util.UUID

data class Category(
    val id: UUID,
    val title: String,
    val image: String,
    val taskCount: Int,
)