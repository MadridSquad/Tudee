package com.washingtondcsquad.tudee.domain.entity

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.washingtondcsquad.tudee.R
import kotlin.text.lowercase

enum class Priority{
    LOW,
    MEDIUM,
    HIGH,
}
fun getPriorityIcon(priority: Priority): Int {
    return when (priority) {
        Priority.LOW -> R.drawable.priority_low
        Priority.MEDIUM -> R.drawable.priority_medium
        Priority.HIGH -> R.drawable.priority_high
    }
}
fun fromStringToPriority(priority: String): Priority {
    return when (priority.lowercase()) {
        "low" -> Priority.LOW
        "medium" -> Priority.MEDIUM
        "high" -> Priority.HIGH
        else -> Priority.LOW
    }
}