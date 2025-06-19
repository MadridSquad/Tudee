package com.washingtondcsquad.tudee.data.localSource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.washingtondcsquad.tudee.data.utils.DataBaseConstants
import com.washingtondcsquad.tudee.domain.entity.Priority
import com.washingtondcsquad.tudee.domain.entity.Task
import com.washingtondcsquad.tudee.domain.entity.TaskStatus

@Entity(tableName = DataBaseConstants.TASK_TABLE_NAME)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val categoryId: Long,
    val categoryImage: String,
    val title: String,
    val description: String,
    val date: String,
    val status: String,
    val priority: String,
) {
    fun toDomain(): Task = Task(
        id = id,
        categoryId = categoryId,
        categoryImage = categoryImage,
        title = title,
        description = description,
        date = date,
        status = when(status) {
            "TODO" -> TaskStatus.TODO
            "IN_PROGRESS" -> TaskStatus.IN_PROGRESS
            else -> TaskStatus.DONE
        },
        priority = when(priority) {
            "LOW" -> Priority.LOW
            "MEDIUM" -> Priority.MEDIUM
            else -> Priority.HIGH
        }
    )

    companion object {
        fun fromDomain(task: Task): TaskEntity = TaskEntity(
            id = task.id,
            categoryId = task.categoryId,
            categoryImage = task.categoryImage,
            title = task.title,
            description = task.description,
            date = task.date,
            status = task.status.name,
            priority = task.priority.name
        )
    }
}
