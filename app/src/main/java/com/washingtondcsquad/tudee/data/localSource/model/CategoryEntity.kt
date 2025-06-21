package com.washingtondcsquad.tudee.data.localSource.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.washingtondcsquad.tudee.domain.entity.CategoryID

@Entity(tableName = CategoryEntity.CATEGORY_TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: CategoryID,
    @ColumnInfo(name = "Category Title") val title: String,
    @ColumnInfo(name = "Category Image") val image: String,
    @ColumnInfo(name = "Task Count") val taskCount: Int,
    @ColumnInfo(name = "Is Predefined") val isPredefined: Boolean
) {
    companion object {
        const val CATEGORY_TABLE_NAME = "category_table"
    }
}