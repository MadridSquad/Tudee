package com.washingtondcsquad.tudee.domain.entity

enum class Priority{
    LOW,
    MEDIUM,
    HIGH;

    companion object {
        fun fromString(priority: String): Priority {
            return when (priority.lowercase()) {
                "low" -> LOW
                "medium" -> MEDIUM
                "high" -> HIGH
                else -> LOW
            }
        }
    }
}