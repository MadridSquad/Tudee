package com.washingtondcsquad.tudee.domain.entity

enum class Priority{
    LOW,
    MEDIUM,
    HIGH,
}

fun fromStringToPriority(priority: String): Priority {
    return when (priority.lowercase()) {
        "low" -> Priority.LOW
        "medium" -> Priority.MEDIUM
        "high" -> Priority.HIGH
        else -> Priority.LOW
    }
}