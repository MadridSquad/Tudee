package com.washingtondcsquad.tudee.data.localSource.mapper.category

import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.fromStringToPriority
import java.time.Instant
import java.time.ZoneId
import java.util.UUID


fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id.toString(),
        categoryId = categoryId,
        title = title,
        description = description,
        date = date.toEpochDay(),
        status = status,
        priority = priority.name
    )
}


fun TaskEntity.toDomain(): Task {
    return Task(
        id = UUID.fromString(id),
        categoryId = categoryId,
        title = title,
        description = description?: "",
        date = Instant.ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),
        status = status,
        priority = fromStringToPriority(priority)
    )
}
