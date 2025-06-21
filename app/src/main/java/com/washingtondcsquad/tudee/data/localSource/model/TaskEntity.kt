package com.washingtondcsquad.tudee.data.localSource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.TaskID

@Entity(tableName = TaskEntity.TASK_TABLE_NAME)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: TaskID,
    val categoryId: CategoryID,
    val title: String,
    val description: String,
    val date: String,
    val status: String,
    val priority: String,
) {
    companion object{
        const val TASK_TABLE_NAME = "task_table"
    }
}
