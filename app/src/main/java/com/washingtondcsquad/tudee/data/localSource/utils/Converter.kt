package com.washingtondcsquad.tudee.data.localSource.utils

import androidx.room.TypeConverter
import com.washingtondcsquad.tudee.domain.entity.CategoryID
import com.washingtondcsquad.tudee.domain.entity.TaskID
import kotlinx.datetime.LocalDate

class Converter {

    @TypeConverter
    fun fromCategoryID(categoryID: CategoryID): Long {
        return categoryID.categoryId
    }

    @TypeConverter
    fun toCategoryID(value: Long): CategoryID {
        return CategoryID(value)
    }

    @TypeConverter
    fun fromTaskID(taskID: TaskID): Long {
        return taskID.taskId
    }

    @TypeConverter
    fun toTaskID(value: Long): TaskID {
        return TaskID(value)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }
}
