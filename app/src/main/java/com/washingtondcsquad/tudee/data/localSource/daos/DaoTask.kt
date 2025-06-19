package com.washingtondcsquad.tudee.data.localSource.daos

import androidx.room.Dao
import androidx.room.Query
import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.data.utils.DataBaseConstants

@Dao
interface DaoTask {
    @Query("SELECT * FROM ${DataBaseConstants.TASK_TABLE_NAME}")
    suspend fun getAllTasks(): List<TaskEntity>
}