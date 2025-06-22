package com.washingtondcsquad.tudee.presentation.utils

import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Priority.HIGH
import com.washingtondcsquad.tudee.domain.entity.Priority.LOW
import com.washingtondcsquad.tudee.domain.entity.Priority.MEDIUM
import com.washingtondcsquad.tudee.domain.entity.Priority.UNKNOWN

fun priorityFromString(priority: String): Priority =
    when (priority.lowercase()) {
        "low" -> LOW
        "medium" -> MEDIUM
        "high" -> HIGH
        else -> UNKNOWN
    }