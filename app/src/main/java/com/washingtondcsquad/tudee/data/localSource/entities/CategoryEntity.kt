package com.washingtondcsquad.tudee.data.localSource.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.washingtondcsquad.tudee.data.utils.Constants


@Entity(tableName = Constants.ROOM_DATABASE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "Category Title") val title: String,
    @ColumnInfo(name = "Category Image") val image: String,
    @ColumnInfo(name = "Task Count") val taskCount: Int,

    )
