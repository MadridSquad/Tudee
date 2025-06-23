package com.washingtondcsquad.tudee.data.localSource.mapper

import com.washingtondcsquad.tudee.data.localSource.model.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus
import kotlinx.datetime.LocalDate


fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        categoryId = CategoryID(categoryId),
        title = title,
        description = description,
        date = date.toString(),
        status = status.name,
        priority = priority.name,
    )
}


fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        categoryId = categoryId.categoryId,
        title = title,
        description = description,
        date = LocalDate.parse(date),
        status = when(status){
            "TODO" -> TaskStatus.TODO
            "IN_PROGRESS" -> TaskStatus.IN_PROGRESS
            else -> TaskStatus.DONE
        },
        priority = when(priority){
            "LOW" -> Priority.LOW
            "MEDIUM" -> Priority.MEDIUM
            else -> Priority.HIGH
        },
    )
}
