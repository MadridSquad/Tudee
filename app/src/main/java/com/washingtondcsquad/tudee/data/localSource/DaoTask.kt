package com.washingtondcsquad.tudee.data.localSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.washingtondcsquad.tudee.data.localSource.entities.TaskEntity
import com.washingtondcsquad.tudee.data.utils.DataBaseConstants

@Dao
interface DaoTask {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: TaskEntity)

    @Update
    suspend fun editTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM ${DataBaseConstants.TASK_TABLE_NAME}")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("SELECT * FROM ${DataBaseConstants.TASK_TABLE_NAME} WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): TaskEntity?
}