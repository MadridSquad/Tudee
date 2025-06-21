package com.washingtondcsquad.tudee.domain.entity

enum class Priority{
    LOW,
    MEDIUM,
    HIGH;

    //TODO replace it by mapping from domain to presentation
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