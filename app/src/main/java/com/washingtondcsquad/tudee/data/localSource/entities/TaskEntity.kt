package com.washingtondcsquad.tudee.data.localSource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.washingtondcsquad.tudee.data.utils.DataBaseConstants

@Entity(tableName = DataBaseConstants.TASK_TABLE_NAME)
data class TaskEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "category_id")
    val categoryId: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "priority")
    val priority: String,
)