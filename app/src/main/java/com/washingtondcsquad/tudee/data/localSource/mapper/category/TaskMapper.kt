package com.washingtondcsquad.tudee.data.localSource.mapper.category

import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus


fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        categoryId = categoryId,
        title = title,
        description = description,
        date = date,
        status = status.name,
        priority = priority.name,
        categoryImage =categoryImage
    )
}


fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        categoryId = categoryId,
        title = title,
        description = description,
        date = date,
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
        categoryImage = categoryImage
    )
}
