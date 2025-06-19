package com.washingtondcsquad.tudee.presentation.utils

import com.washingtondcsquad.tudee.domain.entity.TaskStatus

fun TaskStatus.toDisplayName(): String = when (this) {
    TaskStatus.TODO -> "To-Do"
    TaskStatus.IN_PROGRESS -> "in progress"
    TaskStatus.DONE -> "Done"
}

fun TaskStatus.next(): TaskStatus = when (this) {
    TaskStatus.TODO -> TaskStatus.IN_PROGRESS
    TaskStatus.IN_PROGRESS -> TaskStatus.DONE
    TaskStatus.DONE -> TaskStatus.DONE
}
